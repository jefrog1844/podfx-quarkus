package com.jcr.podfx.jwt;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

import com.jcr.podfx.business.users.entity.User;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

/**
 * Utilities for generating a JWT for testing
 */
public class TokenUtils {

    private static final String ISSUER = ConfigProvider.getConfig().getValue("mp.jwt.verify.issuer", String.class);
    private static final String AUDIENCE = ConfigProvider.getConfig().getValue("com.podfx.jwt.audience", String.class);
    private static final String JTI = ConfigProvider.getConfig().getValue("com.podfx.jwt.jti", String.class);
    private static final String PRIVATE_KEY = ConfigProvider.getConfig().getValue("mp.jwt.verify.privatekey.location",
            String.class);
    private static final Integer EXPIRES = ConfigProvider.getConfig().getValue("com.podfx.jwt.expires", Integer.class);

    private TokenUtils() {
        // no-op: utility class
    }

    /**
     * Utility method to generate a JWT string from a JSON resource file that is
     * signed by the privateKey.pem test resource key, possibly with invalid fields.
     *
     * @param timeClaims - used to return the exp, iat, auth_time claims
     * @return the JWT string
     * @throws Exception on parse failure
     */
    public static String generateTokenString(User user, Map<String, Long> timeClaims) throws Exception {
        // Use the test private key associated with the test public key for a valid
        // signature
        PrivateKey pk = readPrivateKey(PRIVATE_KEY);
        String token = generateTokenString(pk, PRIVATE_KEY, user, timeClaims);
        return token;
    }

    public static String generateTokenString(PrivateKey privateKey, String kid, User user, Map<String, Long> timeClaims)
            throws Exception {
        return getClaims(user, timeClaims).jws().keyId(kid).sign(privateKey);
    }

    private static JwtClaimsBuilder getClaims(User user, Map<String, Long> timeClaims) {
        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer(ISSUER);
        claims.audience(AUDIENCE);

        claims.groups(user.roles.stream().map(r -> r.name).collect(Collectors.toSet()));

        claims.upn(user.email);
        claims.subject(user.username);
        claims.preferredUserName(user.firstName);
        claims.claim("jti", JTI);
        claims.claim(("tenant"), user.tenant);

        long currentTimeInSecs = currentTimeInSecs();
        long exp = timeClaims != null && timeClaims.containsKey(Claims.exp.name()) ? timeClaims.get(Claims.exp.name())
                : currentTimeInSecs + EXPIRES;

        claims.issuedAt(currentTimeInSecs);
        claims.claim(Claims.auth_time.name(), currentTimeInSecs);
        claims.expiresAt(exp);

        return claims;
    }

    /**
     * Read a PEM encoded private key from the classpath
     *
     * @param pemResName - key file resource name
     * @return PrivateKey
     * @throws Exception on decode failure
     */
    public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
        InputStream contentIS = TokenUtils.class.getResourceAsStream(pemResName);
        byte[] tmp = new byte[4096];
        int length = contentIS.read(tmp);
        return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
    }

    public static RSAPublicKey readPublicKey(final String pemResName) throws Exception {
        InputStream contentIS = TokenUtils.class.getResourceAsStream(pemResName);
        byte[] tmp = new byte[4096];
        int length = contentIS.read(tmp);
        return decodePublicKey(new String(tmp, 0, length, "UTF-8"));
    }

    /**
     * Decode a PEM encoded private key string to an RSA PrivateKey
     *
     * @param pemEncoded - PEM string for private key
     * @return PrivateKey
     * @throws Exception on decode failure
     */
    public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
        byte[] encodedBytes = toEncodedBytes(pemEncoded);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    public static RSAPublicKey decodePublicKey(final String pemEncoded) throws Exception {
        byte[] encodedBytes = toEncodedBytes(pemEncoded);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(keySpec);
    }

    private static byte[] toEncodedBytes(final String pemEncoded) {
        final String normalizedPem = removeBeginEnd(pemEncoded);
        return Base64.getDecoder().decode(normalizedPem);
    }

    private static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }

    /**
     * @return the current time in seconds since epoch
     */
    public static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }

}

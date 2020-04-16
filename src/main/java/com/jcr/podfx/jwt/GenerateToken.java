package com.jcr.podfx.jwt;

import com.jcr.podfx.business.users.entity.User;
import java.util.HashMap;
import org.eclipse.microprofile.config.ConfigProvider;

import org.eclipse.microprofile.jwt.Claims;

public class GenerateToken {
    private static final int EXPIRES_SECONDS = ConfigProvider.getConfig().getValue("com.podfx.jwt.expires", Integer.class);
    
    public static String token(User user) throws Exception {

        HashMap<String, Long> timeClaims = new HashMap<>();

        long exp = TokenUtils.currentTimeInSecs() + EXPIRES_SECONDS;
        timeClaims.put(Claims.exp.name(), exp);

        String token = TokenUtils.generateTokenString(user, timeClaims);
        return token;
    }
}

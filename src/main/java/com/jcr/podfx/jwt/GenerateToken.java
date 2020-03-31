package com.jcr.podfx.jwt;

import com.jcr.podfx.business.users.entity.User;
import java.util.HashMap;

import org.eclipse.microprofile.jwt.Claims;

public class GenerateToken {

    public static String token(User user) throws Exception {

        HashMap<String, Long> timeClaims = new HashMap<>();

        long duration = 300;
        long exp = TokenUtils.currentTimeInSecs() + duration;
        timeClaims.put(Claims.exp.name(), exp);

        String token = TokenUtils.generateTokenString(user, timeClaims);
        return token;
    }
}

package com.example.extra;

import org.joda.time.DateTime;

import java.security.Key;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class jwtCreador {

    public static String crearJwt(Key key, DateTime iat,DateTime nbf, DateTime ex, String audiencia, Map claims)
    {

        JwtBuilder jwt = Jwts.builder().setAudience(audiencia);

        jwt.addClaims(claims);

        jwt.setIssuedAt(iat.toDate())
        .setNotBefore(nbf.toDate())
        .setExpiration(ex.toDate())
        .signWith(key);


        return jwt.compact();
    }

    public static String extraerJwt(String jwt ,Key key)
    {
         String result = null;

        try {
            Jws<Claims> jwtParse = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);

            result = jwtParse.getBody().get("message").toString();
        }
        catch(JwtException e)
        {
            e.printStackTrace();
        }

        return result;
    }




}

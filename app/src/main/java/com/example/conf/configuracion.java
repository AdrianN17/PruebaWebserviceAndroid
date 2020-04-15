package com.example.conf;

import java.security.Key;
import java.util.Base64;

import io.jsonwebtoken.security.Keys;

public class configuracion
{
    public static final String KEY_DEFAULT_STRING = "pG1VvOtDZMtZAylyM+k62Ut1AxQWFUmodq0hj9+TGBs=";
    public static final String IP = "http://192.168.0.3/";
    public static final Key KEY_DEFAULT =  Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY_DEFAULT_STRING));
}

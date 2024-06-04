package com.j3thr0.trueke.truekeapi.settings.security.error;

public class JwtTokenException extends RuntimeException{
    public JwtTokenException(String msg){
        super(msg);
    }
}

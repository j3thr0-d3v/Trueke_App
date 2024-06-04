package com.j3thr0.trueke.truekeapi.settings.security.jwt.refresh;

import com.j3thr0.trueke.truekeapi.settings.security.error.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {
    public RefreshTokenException(String msg){
        super(msg);
    }
}

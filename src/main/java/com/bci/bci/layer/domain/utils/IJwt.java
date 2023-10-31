package com.bci.bci.layer.domain.utils;

public interface IJwt {
    String generateJWT(String email);

    String getClaim(String token, String key);
}

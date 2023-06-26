package com.api.data.auth;

public class CreateTokenBuilder {

    public static CreateTokenData setTokenData() {
        return CreateTokenData.builder()
                .username("admin")
                .password("password123")
                .build();
    }

    public static CreateTokenData setWrongTokenData() {
        return CreateTokenData.builder()
                .username("i")
                .password("123")
                .build();
    }
}
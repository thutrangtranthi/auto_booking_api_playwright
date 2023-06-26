package com.api.data.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateTokenData {

    private String username;
    private String password;

}
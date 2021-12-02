package com.CRUD.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LogoutDto {
    private String accessToken;
    private String refreshToken;
}

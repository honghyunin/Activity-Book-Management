package com.CRUD.test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private Long idx;
    private String id;
    private String pw;
}

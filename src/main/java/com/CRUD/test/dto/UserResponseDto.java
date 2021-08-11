package com.CRUD.test.dto;

import com.CRUD.test.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long idx;
    private String id;
    private String pw;

    public UserResponseDto(User entity){
        this.idx = entity.getIdx();
        this.id = entity.getId();
        this.pw = entity.getPw();
    }
}

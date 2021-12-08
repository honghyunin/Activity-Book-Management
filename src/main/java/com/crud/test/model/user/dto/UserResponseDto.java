package com.crud.test.model.user.dto;

import com.crud.test.model.user.User;
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

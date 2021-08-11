package com.CRUD.test.dto;

import com.CRUD.test.domain.Role;
import com.CRUD.test.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;

@Getter @Setter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String id;
    private String pw;

    public User toEntity(){
        return User.builder()
                .id(id)
                .pw(pw)
                .roles(Collections.singletonList(Role.ROLE_NOT_AUTH))
                .build();
    }
}

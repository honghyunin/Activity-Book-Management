package com.CRUD.test.dto;

import com.CRUD.test.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String id;
    private String pw;

    public User toEntity(){
        return User.builder()
                .id(id)
                .pw(pw)
                .build();
    }
}

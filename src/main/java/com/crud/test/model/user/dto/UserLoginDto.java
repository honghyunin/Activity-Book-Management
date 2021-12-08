package com.crud.test.model.user.dto;


import com.crud.test.model.user.Role;
import com.crud.test.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {
    private String id;
    private String pw;

    public UserLoginDto(String id, String pw){
        this.id=id;
        this.pw=pw;
    }
    public User toEntity(){
        return User.builder()
                .id(id)
                .pw(pw)
                .roles(Collections.singletonList(Role.ROLE_USER))
                .build();
    }
}

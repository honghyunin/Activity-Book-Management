package com.crud.test.model.user.dto;

import com.crud.test.model.user.Role;
import com.crud.test.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;

@Getter @Setter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String id;
    private String pw;
    private String name;
    private String userNumber;
    private Boolean rentalAbleUser = true;

    public User toEntity(){
        return User.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .userNumber(userNumber)
                .rentalAbleUser(rentalAbleUser)
                .roles(Collections.singletonList(Role.ROLE_NOT_AUTH))
                .build();
    }
}

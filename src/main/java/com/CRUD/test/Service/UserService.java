package com.CRUD.test.Service;

import com.CRUD.test.domain.User;
import com.CRUD.test.dto.UserSaveRequestDto;
import com.CRUD.test.dto.UserUpdateRequestDto;

import java.util.Optional;

public interface UserService {
    Long save(UserSaveRequestDto user);
    String findById(Long idx);
    String update(UserUpdateRequestDto requestDto);
    String delete(Long idx);
}

package com.CRUD.test.Service;

import com.CRUD.test.domain.User;
import com.CRUD.test.dto.UserResponseDto;
import com.CRUD.test.dto.UserSaveRequestDto;
import com.CRUD.test.dto.UserUpdateRequestDto;

import java.util.Optional;

public interface UserService {
    Long save(UserSaveRequestDto user);
    UserResponseDto findById(Long idx);
    Long update(UserUpdateRequestDto requestDto);
    String delete(Long idx);
}

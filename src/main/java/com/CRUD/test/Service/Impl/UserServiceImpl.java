package com.CRUD.test.Service.Impl;

import com.CRUD.test.Service.UserService;
import com.CRUD.test.advice.exception.UserNotFoundException;
import com.CRUD.test.domain.User;
import com.CRUD.test.dto.UserSaveRequestDto;
import com.CRUD.test.dto.UserUpdateRequestDto;
import com.CRUD.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public Long save(UserSaveRequestDto user) {
        return userRepository.save(user.toEntity()).getIdx();
    }

    @Override
    public String findById(Long idx) {
        return idx+ "열 조회 성공!";
    }

    @Transactional
    @Override
    public String update(UserUpdateRequestDto requestDto) {
        Long idx = requestDto.getIdx();
        User user = userRepository.findById(idx).orElseThrow(UserNotFoundException :: new);
        user.update(requestDto.getId(), requestDto.getPw());
        return user + "업데이트 성공";
    }

    @Override
    public String delete(Long idx) {
        userRepository.deleteById(idx);
        return  idx +"is Delete" ;
    }


}

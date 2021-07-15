package com.CRUD.test.Service.Impl;

import com.CRUD.test.Service.UserService;
import com.CRUD.test.advice.exception.UserAlreadyExistsException;
import com.CRUD.test.advice.exception.UserNotFoundException;
import com.CRUD.test.domain.User;
import com.CRUD.test.dto.UserResponseDto;
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
        if(userRepository.findById(user.getId()) != null){
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user.toEntity()).getIdx();
    }

    @Override
    public UserResponseDto findById(Long idx) {
            User user = userRepository.findById(idx)
                    .orElseThrow(UserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    @Override
    public Long update(UserUpdateRequestDto requestDto) {
        Long idx = requestDto.getIdx();
        User user = userRepository.findById(idx).orElseThrow(UserNotFoundException :: new);
        user.update(requestDto.getId(), requestDto.getPw());
        return idx;
    }

    @Override
    public String delete(Long idx) {
        userRepository.deleteById(idx);
        return  idx +"is Delete" ;
    }


}

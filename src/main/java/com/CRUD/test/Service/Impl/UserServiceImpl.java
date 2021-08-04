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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long save(UserSaveRequestDto user) {
        if(userRepository.findById(user.getId()) != null){ // idx는 Auto increment이므로 Dto에 담기지 않았기에 대신 id를 조건으로 걸었음
            throw new UserAlreadyExistsException();
        }
        user.setPw(passwordEncoder.encode(user.getPw()));
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
        Long idx = requestDto.getIdx(); // idx 지정
        User user = userRepository.findById(idx).orElseThrow(UserNotFoundException :: new);  // localDB에 존재하는 idx의 값을 찾고 그 값이 없을 경우의 예외를 처리함
        user.update(requestDto.getId(), requestDto.getPw());
        return idx;
    }

    @Override
    public String delete(Long idx) {
        userRepository.deleteById(idx); // 내 localDB에 존재하는 idx의 열을 삭제함
        return  idx +"is Delete" ;
    }


}

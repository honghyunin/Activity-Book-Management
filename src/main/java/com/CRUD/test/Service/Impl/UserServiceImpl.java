package com.CRUD.test.Service.Impl;

import com.CRUD.test.Service.UserService;
import com.CRUD.test.dto.UserSaveRequestDto;
import com.CRUD.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
        return userRepository.findById(idx).get().getId()+"\n" + userRepository.findById(idx).get().getPw();
    }


}

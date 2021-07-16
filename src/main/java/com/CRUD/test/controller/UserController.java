package com.CRUD.test.controller;

import com.CRUD.test.Service.UserService;
import com.CRUD.test.dto.UserResponseDto;
import com.CRUD.test.dto.UserSaveRequestDto;
import com.CRUD.test.dto.UserUpdateRequestDto;
import com.CRUD.test.respose.CommonResult;
import com.CRUD.test.respose.ResponseService;
import com.CRUD.test.respose.SingleResult;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController { // (2) HTTP Request에서 들어온 요청을 받아와 그에 맞는 서비스 로직을 실행시켜주는 구간
    private final UserService userService;
    private final ResponseService responseService;

    @PostMapping("/save")
    public SingleResult<Long> save(@RequestBody UserSaveRequestDto user){
        return responseService.getSingleResult(userService.save(user));
    }

    @GetMapping("/read")
    public SingleResult<UserResponseDto> findByUserId(@RequestParam Long idx){
        return responseService.getSingleResult(userService.findById(idx));
    }

    @PutMapping("/update")
    public SingleResult<Long> update(@RequestBody UserUpdateRequestDto requestDto) {
        return responseService.getSingleResult(userService.update(requestDto));
    }

    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam Long idx){
        return responseService.getSingleResult(userService.delete(idx));
    }
}

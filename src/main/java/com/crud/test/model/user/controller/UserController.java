package com.crud.test.model.user.controller;

import com.crud.test.model.book.Book;
import com.crud.test.model.user.repository.UserRepository;
import com.crud.test.model.user.service.EmailSenderService;
import com.crud.test.model.user.service.Impl.UserServiceImpl;
import com.crud.test.model.user.dto.*;
import com.crud.test.global.response.CommonResult;
import com.crud.test.global.response.ResponseService;
import com.crud.test.global.response.SingleResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController { // (2) HTTP Request에서 들어온 요청을 받아와 그에 맞는 서비스 로직을 실행시켜주는 구간
    private final UserServiceImpl userService;
    private final ResponseService responseService;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
/*
    @PostMapping("/vertifyCode/")
    public SingleResult<Integer> execMail(@RequestBody EmailAuth emailAuth ){
        return responseService.getSingleResult(emailSenderService.Emailcertification(emailAuth));
    }*/

    @PostMapping("/signin") @ApiOperation(value="로그인", notes = "로그인")
    public SingleResult<Map<String, String>> login(@RequestBody UserLoginDto user){
        return responseService.getSingleResult(userService.login(user));
    }

    @PostMapping("/signup") @ApiOperation(value="회원가입", notes = "회원가입")
    public SingleResult<Long> save(@RequestBody UserSaveRequestDto user){
        return responseService.getSingleResult(userService.signup(user));
    }

    @GetMapping("/read") @ApiOperation(value="유저 조회", notes = "유저 조회")
    public CommonResult findByUserId(@RequestParam Long idx){
        return responseService.getSingleResult(userRepository.findById(idx));
    }

    @PutMapping("/update") @ApiOperation(value="비밀번호 변경", notes = "비밀번호 변경")
    public SingleResult<Long> update(@RequestBody UserUpdateRequestDto requestDto) {
        return responseService.getSingleResult(userService.update(requestDto));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
    })
    @DeleteMapping("/logout") @ApiOperation(value="로그아웃", notes = "로그아웃")
    public CommonResult logout(){
        userService.logout();
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/delete") @ApiOperation(value="회원탈퇴", notes = "회원탈퇴")
    public SingleResult<Long> delete(@RequestParam Long idx){
        return responseService.getSingleResult(userService.delete(idx));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
    })
    @GetMapping("/find")
    public SingleResult<List<Book>> findRentalBook(){
        return responseService.getSingleResult(userService.userRentalBook());
    }

}

package com.CRUD.test.controller;

import com.CRUD.test.Service.UserService;
import com.CRUD.test.dto.UserSaveRequestDto;
import com.CRUD.test.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public String save(@RequestBody UserSaveRequestDto user){
        userService.save(user);
        return "Success";
    }

    @GetMapping("/read")
    public String findByUserId(@RequestParam Long idx){

        return "조회 결과"+userService.findById(idx);
    }

    @PutMapping("/update")
    public String update(@RequestBody UserUpdateRequestDto requestDto) {
        return "업데이트 결과";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long idx){
        userService.delete(idx);
        return "delete";
    }
}

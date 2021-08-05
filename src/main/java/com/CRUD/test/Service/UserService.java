package com.CRUD.test.Service;

import com.CRUD.test.domain.User;
import com.CRUD.test.dto.UserLoginDto;
import com.CRUD.test.dto.UserResponseDto;
import com.CRUD.test.dto.UserSaveRequestDto;
import com.CRUD.test.dto.UserUpdateRequestDto;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    // 현업에서 주로 사용하는 방식으로 비즈니스 로직을 처리하는 모델은 요청사항에 따라 언제든 변할 수 있는 부분이었고
    // 변화에 대응하기 위해 확장을 염두하여 인터페이스로 구성했던 것이다
    Long save(UserSaveRequestDto user); // 데이터를 local DB에 저장하는 메소드
    UserResponseDto findById(Long idx); // local DB안에 있는 데이터를 요청으로 들어온 Long타입의 idx값으로 찾는 메소드
    Long update(UserUpdateRequestDto requestDto); // idx 열에 있는 데이터를 요청으로 받아온 id와 pw의 값으로 변경해주는 메소드
    String delete(Long idx); // idx값의 열을 삭제해주는 메소드(데이터 또한)
    Map<String, String> login(UserLoginDto user);
}

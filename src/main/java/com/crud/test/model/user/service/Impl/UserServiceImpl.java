package com.crud.test.model.user.service.Impl;

import com.crud.test.model.book.Book;
import com.crud.test.model.rental.Rental;
import com.crud.test.model.rental.repository.RentalCustomRepository;
import com.crud.test.model.rental.repository.RentalRepository;
import com.crud.test.model.user.service.UserService;
import com.crud.test.global.advice.exception.UserNotFoundException;
import com.crud.test.global.advice.exception.UserNotmatch;
import com.crud.test.model.user.User;
import com.crud.test.model.user.dto.*;
import com.crud.test.model.user.repository.UserRepository;
import com.crud.test.global.security.JwtTokenProdvider;
import com.crud.test.global.util.CurrentUserUtil;
import com.crud.test.global.util.RedisUtil;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProdvider jwtTokenProdvider;
    private final RedisUtil redisUtil;
    private final CurrentUserUtil currentUserUtil;
    private final RentalCustomRepository rentalCustomRepository;

    @Transactional
    public Long signup(UserSaveRequestDto user) {
        if(userRepository.findById(user.getId()).isPresent()){
            throw new IllegalArgumentException("유저를 찾을 수 없습니다");
        }

        user.setPw(passwordEncoder.encode(user.getPw())); // password 암호화

        return userRepository.save(user.toEntity()).getIdx(); // 우선적으로 DB에 저장함
    }

    public Map<String, String> login(UserLoginDto user){

        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다")); // 해당 예외처리 같은 경우는 프론트단에서 문제가 발생할 수 있으므로 추후 리팩토링 시 변경함

        if(!passwordEncoder.matches(user.getPw(), findUser.getPw()))
        {
            throw new UserNotmatch("일치하지 않는 비밀번호입니다");
        }

        String AccessToken = jwtTokenProdvider.createToken(user.getId(), user.toEntity().getRoles()); //, user.toEntity().getRoles()
        String RefreshToken = jwtTokenProdvider.createRefreshToken();

        redisUtil.deleteData(user.getId()); // redis에 값을 삽입하기 전 해당 아이디 삭제하나용??
        // redis에 값을 삽입하기전에 redis 내부에 있는 id 값을 삭제한다.
        // 왜냐하면 하나의 유저는 하나의 토큰을 가지는 법칙이 존재하므로
        // 유저가 로그아웃을 하지 않고 유효기간이 만료된 토큰이 생길 경우 법칙에 위배되며, 토큰이 여러 개가 생길 경우 토큰을 관리하는 과정에서 여러가지 문제점이 발생하므로
        // 토큰을 삽입하기전에 key를 삭제하므로써 이를 방지한다.
        redisUtil.setDataExpire(user.getId(), RefreshToken, JwtTokenProdvider.REFRESH_TOKEN_VAILD_TIME);

        Map<String, String> map = new HashMap<>();
        map.put("ID", user.getId()); // map은 클라이언트에게 넘어가는 데이터이다. 그러므로 이 토큰을 어떤 유저가 가졌는 지 알기 위함.
        // 굳이 추가하지 않아도 되지만 클라이언트와 상호 협의하에 결정해도 될 문제.

        map.put("AccessToken", "Bearer "+AccessToken);
        map.put("RefreshToken", "Bearer " +RefreshToken);
        // 'Bearer'으로 토큰이 유효한지 인증한다.
        // Token에 'Bearer'이 있을 경우 해당 토큰이 인증 됨을 나타내므로
        // Bearer을 토큰에 추가해준다.

        return map;
    }

    public UserResponseDto findById(Long idx) {
            User user = userRepository.findById(idx)
                    .orElseThrow(UserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    public Long update(UserUpdateRequestDto requestDto) {
        Long idx = requestDto.getIdx(); // idx 지정
        User user = userRepository.findById(idx).orElseThrow(UserNotFoundException :: new);  // localDB에 존재하는 idx의 값을 찾고 그 값이 없을 경우의 예외를 처리함
        user.update(requestDto.getId(), requestDto.getPw());
        return idx;
    }

    public void logout(){
        redisUtil.deleteData(currentUserUtil.getCurrentUser().getId());
    }

    public Long delete(Long idx) {
        userRepository.deleteById(idx); // 내 localDB에 존재하는 idx의 열을 삭제함
        return idx;
    }

    public List<Book> userRentalBook(){
        User user = currentUserUtil.getCurrentUser();
        List<Book> rental = rentalCustomRepository.findUserRental(user);

        return rental;
    }
}

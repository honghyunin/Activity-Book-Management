package com.crud.test.global.security;


import com.crud.test.model.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component // 개발자가 사용하기 위해 직접 작성한 CLass는 Bean으로 등록하기 위해서 Component 어노테이션을 달아준다
public class JwtTokenProdvider {

    private String secretKey = "honghyunin0102"; // 이 필드를 통해 아이디 로그인 인증이 끝난 후 다시 요청이 들어올 때 요청이 유효한지 검증함
    // 프로퍼티 파일에 값을 따로 저장함으로써 코드상에서 시크릿 키의 값을 드러내지 않게 함(프포퍼티파일은  gitgnore로 소스코드 상에서 드러내지 않도록 함
    // 토큰의 유효시간 설정
    public final static Long TOKEN_VAILD_TIME = 1000L * 60; // 1분
    public final static Long REFRESH_TOKEN_VAILD_TIME = 1000L * 120; // 1시간
    // 액세스 토큰의 유효기간을 정함. 액세스 토큰의 시간을 짧게하되 리프레쉬 토큰을 길게 함으로써 지속적으로 비밀번호를 재암호화 시켜 보안성을 높힘

    private final MyUserDetails myUserDetails; // DB에서 유저정보를 가져오는 역할을 함

    @PostConstruct //다른 리소스에서 호출되지 않아도 의존성 주입이 끝난 뒤 수행하게 하는 어노테이션
    protected void init(){ //의존성 주입이 이루어진 후 초기화를 수행하는 메소드
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        // secretKey를 Base64(암호화 알고리즘)으로 인코딩(암호화) 시킨다.
    }
    public String createToken(String userId, List<Role> roles){ //
        // claims : JWT payload에 저장되는 정보단위
        Claims claims = Jwts.claims().setSubject(userId); // claims의 제목을 userId로 세팅한다
        claims.put("auth", roles.stream() // claims 에 권한 정보를 추가
                .map(s -> new SimpleGrantedAuthority(s.getAuthority())) // 권한 부여
                .filter(Objects::nonNull).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + TOKEN_VAILD_TIME);// 유효시간 선언 [ 지금시간 + expireTime 시간 ]
        return Jwts.builder()
                .setClaims(claims) //정보저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(validity) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 signature에 들어갈 secret값 세팅팅
                .compact();
    }
    public String createRefreshToken(){
        Claims claims = Jwts.claims().setSubject(null);

        Date now = new Date(); // /토큰이 생성된 시간으로부터 유효기간을 설정해야하므로 현재시간을 가져올 수 있도록 함
        Date validity = new Date(now.getTime() + REFRESH_TOKEN_VAILD_TIME); //Expire Time + 현재시간

        return Jwts.builder() // refreshtoken에 담을 정보를 set하는 중
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    //유저 정보를 가져오는 메소드
    public Authentication getAuthentication(String token){
        String subject = getSubject(token);
        UserDetails userDetails = myUserDetails.loadUserByUsername(subject); // 실제 DB에서 유저의 이름을 가져옴
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()); // 유저의 이름과 비밀번호가 맞는 지 검증
    } //Authentication : 인증

    public String getSubject(String token) { // 테스트를 위해 public으로 전환 이후엔 다시 private으로 변경해야함
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject(); // 토큰에서 시크릿키를 통해 검증하고 claims안에 있는 제목을 가져옴
    }

    // *Request의 header에서 token 값을 가져오는 메소드
    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer")){ // Token 토큰에 Bearer가 없을 경우 인증되지 않은 토큰이다.
            return bearerToken.substring(7);
        }else{
            return null;
        }
    }
    // token이 유효한지 아닌지 검증하는 메소드
    public boolean validateToken(String token) {
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch(Exception e) {
            SecurityContextHolder.clearContext();
            return false;
        }
    }
}

package com.crud.test.global.security;

import com.crud.test.global.advice.exception.CustomException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter { // OncePerRequestFilter 는 요청이 들어올 때마다 실행
    private final JwtTokenProdvider jwtTokenProdvider;
    public JwtTokenFilter(JwtTokenProdvider jwtTokenProdvider){
        this.jwtTokenProdvider = jwtTokenProdvider;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtTokenProdvider.resolveToken(request);
        try{
            if(token != null && jwtTokenProdvider.validateToken(token)){
                Authentication authentication = jwtTokenProdvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch(CustomException e){
            SecurityContextHolder.clearContext();
            response.sendError(e.getHttpStatus().value(), e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }
}

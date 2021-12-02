package com.CRUD.test.util;

import com.CRUD.test.domain.User;
import com.CRUD.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {

    private final UserRepository userRepository;

    public static String getCurrentUserId(){
        String id = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            id = ((UserDetails) principal).getUsername();
        } else{
            id = principal.toString();
        }
        return id;
    }

    public User getCurrentUser() {
        String id = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails) {
            id = ((UserDetails) principal).getUsername();
        } else{
            id = principal.toString();
        }
        User userinfo = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));
        return userinfo;
    }
}

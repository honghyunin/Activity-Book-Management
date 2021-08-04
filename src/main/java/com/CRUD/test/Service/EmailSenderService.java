package com.CRUD.test.Service;

import com.CRUD.test.dto.UserSaveRequestDto;
import com.CRUD.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private static final String FROM_ADDRESS = "hyunin0102@gmail.com";

    public SimpleMailMessage sendEmail(UserSaveRequestDto user){
        String vertifyCode = "localhost:8080/api/vertifyCode";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getId());
        message.setFrom(user.getId());
        message.setSubject("Spring-CRUD의 이메일을 인증하세요");
        message.setText("안녕하세요 \n다음 링크를 통해 당신의 이메일을 인증하세요\n");
        javaMailSender.send(message);
        return message;
    }
}

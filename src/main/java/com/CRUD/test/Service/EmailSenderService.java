package com.CRUD.test.Service;

import com.CRUD.test.advice.exception.UserNotmatch;
import com.CRUD.test.domain.User;
import com.CRUD.test.dto.EmailAuth;
import com.CRUD.test.dto.UserSaveRequestDto;
import com.CRUD.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private static final String FROM_ADDRESS = "hyunin0102@gmail.com";
    private final EntityManager entityManager;

    @Setter @Getter
    private double dValue;
    @Setter @Getter
    private int iValue;

    public SimpleMailMessage sendEmail(UserSaveRequestDto user){

        SimpleMailMessage message = new SimpleMailMessage();

        setDValue(Math.random());
        setIValue((int)(getDValue() * 10000));


        message.setTo(user.getId());
        message.setFrom(user.getId());
        message.setSubject("Spring-CRUD의 이메일을 인증하세요");
        message.setText("안녕하세요\n다음 숫자를 통해 당신의 이메일을 인증하세요\n"+getIValue());
        //javaMailSender.send(message);

        return message;
    }

    public int Emailcertification(EmailAuth emailAuth) {
        int iValue = getIValue();
        System.out.println("인증번호 : "+iValue);
        User user = userRepository.findById(emailAuth.getId());
        if(!(iValue == emailAuth.getNumber())){
            userRepository.delete(user);
            throw new UserNotmatch("어허 똑같지 아니하다!!");
        }
        System.out.println("똑같여~");

        return iValue;
    }
}

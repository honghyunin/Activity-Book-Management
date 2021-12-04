package com.CRUD.test.domain.rentalbook.dto;

import com.CRUD.test.domain.book.Book;
import com.CRUD.test.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class RentalReqDto {

    @Getter
    @Setter
    public static class Rental { // 대여
        private Long bookIdx;

        public com.CRUD.test.domain.rentalbook.Rental toEntity(User user, Book book){
            return com.CRUD.test.domain.rentalbook.Rental.builder()
                    .user(user)
                    .book(book)
                    .rental_date(LocalDate.now())
                    .return_date(LocalDate.now().plusDays(7))
                    .build();
        }
    }

    @Getter
    @Setter
    public static class Renew { // 연장

    }
    @Getter @Setter
    public static class Overdue { // 연체

    }

    @Getter
    @Setter
    public static class returnBook { // 반납

    }


}

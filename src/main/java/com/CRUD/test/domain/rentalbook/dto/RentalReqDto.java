package com.CRUD.test.domain.rentalbook.dto;

import lombok.Getter;
import lombok.Setter;

public class RentalReqDto {

    @Getter
    @Setter
    public static class Rental { // 대여

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

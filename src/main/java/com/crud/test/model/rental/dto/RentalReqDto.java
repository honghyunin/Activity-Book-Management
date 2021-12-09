package com.crud.test.model.rental.dto;

import lombok.Getter;
import lombok.Setter;

public class RentalReqDto {

    @Getter
    @Setter
    public static class RentalBook { // 대여
        private Long bookIdx;
    }

    @Getter
    @Setter
    public static class Renew { // 연장
        private Long idx;
    }

    @Getter
    @Setter
    public static class ReturnBook { // 반납
        private Long idx;
    }


}

package com.CRUD.test.domain.rentalbook.controller;

import com.CRUD.test.domain.rentalbook.dto.RentalReqDto;
import com.CRUD.test.domain.rentalbook.service.RentalService;
import com.CRUD.test.global.response.CommonResult;
import com.CRUD.test.global.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/rental")
@RequiredArgsConstructor
@RestController
public class RentalController {

    private final ResponseService responseService;
    private final RentalService rentalBookService;

    @PostMapping("/")
    public CommonResult rental(@RequestBody RentalReqDto.Rental book){
        rentalBookService.rentalBook(book);
        return responseService.getSuccessResult();
    }
}

package com.crud.test.model.rental.controller;

import com.crud.test.model.book.Book;
import com.crud.test.model.rental.dto.RentalReqDto;
import com.crud.test.model.rental.service.RentalService;
import com.crud.test.global.response.CommonResult;
import com.crud.test.global.response.ResponseService;
import com.crud.test.global.response.SingleResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/rental")
@RequiredArgsConstructor
@RestController
public class RentalController {

    private final ResponseService responseService;
    private final RentalService rentalBookService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
    })
    @GetMapping("/{idx}") @ApiOperation(value = "대여")
    public CommonResult rental(@PathVariable("idx") Long idx){
        rentalBookService.rentalBook(idx);
        return responseService.getSuccessResult();
    }

    @GetMapping("/read") @ApiOperation(value = "대여할 수 있는 책 조회")
    public SingleResult<List<Book>> rentalSelect(){
        return responseService.getSingleResult(rentalBookService.selectRentalAbleBook());
    }

    @DeleteMapping("/return/{idx}") @ApiOperation(value = "반납")
    public SingleResult<Long> returnBook(@PathVariable RentalReqDto.ReturnBook book){
        return responseService.getSingleResult(rentalBookService.returnBook(book.getIdx()));
    }

    @PostMapping("/renew/{idx}") @ApiOperation(value = "도서 연장")
    public CommonResult renewBook(@PathVariable Long idx){
        rentalBookService.renewBook(idx);
        return responseService.getSuccessResult();
    }
}

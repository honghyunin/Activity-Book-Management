package com.CRUD.test.domain.book.controller;
import com.CRUD.test.domain.book.Book;
import com.CRUD.test.domain.book.dto.BookRequestDto;
import com.CRUD.test.domain.book.service.BookService;
import com.CRUD.test.global.response.CommonResult;
import com.CRUD.test.global.response.ResponseService;
import com.CRUD.test.global.response.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/book")
@RequiredArgsConstructor
@RestController
public class BookController {

    private final ResponseService responseService;
    private final BookService bookService;

    @PostMapping("/add")
    public SingleResult<Long> addBook(@RequestBody BookRequestDto.Add book) {
        return responseService.getSingleResult(bookService.addBook(book));
    }
}

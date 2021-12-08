package com.crud.test.model.book.controller;
import com.crud.test.model.book.Book;
import com.crud.test.model.book.dto.BookRequestDto;
import com.crud.test.model.book.service.BookService;
import com.crud.test.global.response.CommonResult;
import com.crud.test.global.response.ResponseService;
import com.crud.test.global.response.SingleResult;
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

    @PutMapping("/update")
    public CommonResult updateBook(@RequestBody BookRequestDto.Add book){
        return responseService.getSingleResult(bookService.updateBook(book));
    }

    @DeleteMapping("/delete")
    public SingleResult<Long> deleteBook(@RequestBody BookRequestDto.Delete book) {
        return responseService.getSingleResult(bookService.deleteBook(book));
    }

    @PostMapping("/category")
    public SingleResult<List<Book>> selectBook(@RequestBody BookRequestDto.findBy book){
        return responseService.getSingleResult(bookService.findBook(book));
    }

    @PostMapping("/title")
    public SingleResult<List<Book>> selectBookByTitle(@RequestBody BookRequestDto.findBy book){
        return responseService.getSingleResult(bookService.findBookByTitle(book));
    }

    @GetMapping("/find")
    public SingleResult<List<Book>> selectBookAll(){
        return responseService.getSingleResult(bookService.findAll());
    }
}

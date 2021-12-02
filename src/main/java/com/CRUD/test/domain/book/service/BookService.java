package com.CRUD.test.domain.book.service;

import com.CRUD.test.domain.book.dto.BookRequestDto;
import com.CRUD.test.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Long addBook(BookRequestDto.Add book){
        if(bookRepository.findByTitle(book.getTitle()) != null){
            throw new RuntimeException("이미 존재하는 책입니다");
        }
        return bookRepository.save(book.toEntity()).getIdx();
    }
}

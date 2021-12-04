package com.CRUD.test.domain.book.service;

import com.CRUD.test.domain.book.Book;
import com.CRUD.test.domain.book.dto.BookRequestDto;
import com.CRUD.test.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Long addBook(BookRequestDto.Add book){
        return bookRepository.save(book.toEntity()).getIdx();
    }

    @Transactional
    public String updateBook(BookRequestDto.Add book){
        if(bookRepository.findByIdx(book.getIdx()) == null){
            throw new RuntimeException("존재하지 않는 책입니다");
        }
        Book books = bookRepository.findById(book.getIdx())
                .orElseThrow(IllegalArgumentException::new);

        books.update(book.getTitle(), book.getCategory(), book.getAuthor());
        return "책 정보 수정이 완료되었습니다";
    }
}

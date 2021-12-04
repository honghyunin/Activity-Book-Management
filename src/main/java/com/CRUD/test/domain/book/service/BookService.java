package com.CRUD.test.domain.book.service;

import com.CRUD.test.domain.book.Book;
import com.CRUD.test.domain.book.dto.BookRequestDto;
import com.CRUD.test.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Long deleteBook(BookRequestDto.Delete bookreq){
        Long idx = bookreq.getIdx();
        Book book = bookRepository.findById(idx)
                .orElseThrow(IllegalArgumentException::new);
        bookRepository.delete(book);
        return idx;
    }

    public List<Book> findBook(BookRequestDto.findBy book){
        List<Book> book1 = bookRepository.findByCategory(book.getFindBook());
        if(book1.isEmpty()) throw new RuntimeException("책을 찾을 수 없습니다");
        return book1;
    }

    public List<Book> findBookByTitle(BookRequestDto.findBy book){
        List<Book> books = bookRepository.findByTitle(book.getFindBook());
        if(books.isEmpty()) throw new RuntimeException("책을 찾을 수 없습니다");
        return books;
    }
}

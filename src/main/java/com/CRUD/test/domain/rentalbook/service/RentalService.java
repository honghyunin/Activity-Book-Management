package com.CRUD.test.domain.rentalbook.service;

import com.CRUD.test.domain.book.Book;
import com.CRUD.test.domain.book.repository.BookRepository;
import com.CRUD.test.domain.rentalbook.dto.RentalReqDto;
import com.CRUD.test.domain.rentalbook.repository.RentalRepository;
import com.CRUD.test.domain.user.User;
import com.CRUD.test.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RentalService {
    private final RentalRepository rentalBookRepository;
    private final BookRepository bookRepository;
    private final CurrentUserUtil currentUserUtil;

    public void rentalBook(RentalReqDto.Rental book){
        User user = currentUserUtil.getCurrentUser();
        Book book1 = bookRepository.findByIdx(book.getBookIdx());
        if(book1 == null){
            throw new RuntimeException("책을 찾을 수 없습니다");
        }
        rentalAbleCheck(user, book1);
        rentalBookRepository.save(book.toEntity(user, book1));
    }

    private void rentalAbleCheck(User user, Book book){
        if(!user.isRentalAbleUser()){
            throw new RuntimeException("대출이 불가능합니다");
        }
        if(!book.getRentalAbleBook()){
            throw new RuntimeException("이미 대여된 책입니다");
        }
    }

    public List<Book> selectRentalAbleBook(){
        return bookRepository.findByRentalAbleBook(true);
    }
}

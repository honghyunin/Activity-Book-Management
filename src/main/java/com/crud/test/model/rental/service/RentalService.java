package com.crud.test.model.rental.service;

import com.crud.test.model.book.Book;
import com.crud.test.model.book.repository.BookRepository;
import com.crud.test.model.rental.Rental;
import com.crud.test.model.rental.repository.RentalRepository;
import com.crud.test.model.user.User;
import com.crud.test.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final CurrentUserUtil currentUserUtil;

    public void rentalBook(Long idx){ // 책을 대여해주는 메소드
        User user = currentUserUtil.getCurrentUser(); //현재 로그인 된 유저의 정보를 가져옴
        List<Book> book1 = bookRepository.findByIdx(idx); // 대여할 책을 매개변수로 받아서 Book 테이블에서 해당 책을 찾아옴
        if(book1 == null){
            throw new RuntimeException("책을 찾을 수 없습니다");
        }
        System.out.println("1");
        for(Book book: book1){
            rentalAbleCheck(user, book);
            System.out.println("finish check");
            book.setRentalAbleBook(false);
            if(book.getRentalAbleBook()) // 대출이 가능하면
                System.out.println("rental Able check");
                rentalRepository.save(toEntity(user, book));
            System.out.println("save check");
        }
    }
    private Rental toEntity(User user, Book book){
        return Rental.builder()
                .user(user)
                .book(book)
                .rental_date(LocalDate.now())
                .return_date(LocalDate.now().plusDays(7))
                .build();
    }
    private void rentalAbleCheck(User user, Book book){
        if(!user.isRentalAbleUser()){
            throw new RuntimeException("대출이 불가능합니다");
        }
        if(!book.getRentalAbleBook()){
            throw new RuntimeException("이미 대여된 책입니다");
        }
    }

    public List<Book> selectRentalAbleBook(){ //대여가 가능한 책들을 조회해줌
        return bookRepository.findByRentalAbleBook(true);
    }

    public Long returnBook(Long idx){ // 책 반납
        rentalRepository.deleteById(idx); // Rental Table에 저장된 컬럼을 지움으로써 반납
        return idx;
    }

    @Transactional
    public void renewBook(Long idx){
        Rental rental = rentalRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다"));

        rental.setReturn_date(rental.getReturn_date().plusDays(7));
    }
}

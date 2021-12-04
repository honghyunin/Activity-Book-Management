package com.CRUD.test.domain.book.repository;

import com.CRUD.test.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIdx(Long idx);
    List<Book> findByTitle(String title);
    List<Book> findByCategory(String category);
    List<Book> findByRentalAbleBook(Boolean rentalAbleBook);
}

package com.crud.test.model.book.repository;

import com.crud.test.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIdx(Long id);
    List<Book> findByTitle(String title);
    List<Book> findByCategory(String category);
    List<Book> findByRentalAbleBook(Boolean rentalAbleBook);
}

package com.CRUD.test.domain.book.repository;

import com.CRUD.test.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
}

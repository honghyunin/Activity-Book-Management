package com.crud.test.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id @GeneratedValue
    private Long idx;

    @Column(name = "BOOK_TITLE", nullable = false)
    private String title;

    @Column(name = "BOOK_CATEGORY", nullable = false)
    private String category;

    @Column(name = "BOOK_AUTHOR", nullable = false)
    private String author;

    @Column(name = "RENTAL_ABLE_BOOK", columnDefinition = "boolean default true")
    private Boolean rentalAbleBook;

    public void setRentalAbleBook(Boolean ableBook){
        this.rentalAbleBook = ableBook;
    }

    public void update(String title, String category, String author){
        this.title = title;
        this.category = category;
        this.author = author;
    }
}

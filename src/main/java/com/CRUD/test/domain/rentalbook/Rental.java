package com.CRUD.test.domain.rentalbook;

import com.CRUD.test.domain.book.Book;
import com.CRUD.test.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rental {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "RENTAL_DATE", nullable = false)
    private LocalDate rental_date;

    @Column(name = "RETURN_DATE")
    private LocalDate return_date;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;
}

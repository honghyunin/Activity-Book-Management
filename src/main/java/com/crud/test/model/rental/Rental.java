package com.crud.test.model.rental;

import com.crud.test.model.book.Book;
import com.crud.test.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rental {
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "RENTAL_DATE")
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

package com.crud.test.model.rental.repository;

import com.crud.test.model.book.Book;
import com.crud.test.model.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.crud.test.model.rental.QRental.rental;
import static com.crud.test.model.user.QUser.user;
@AllArgsConstructor
@Repository
public class RentalCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    public List<Book> findUserRental(User users){
        return jpaQueryFactory
                .select(rental.book)
                .from(rental)
                .join(rental.user, user)
                .where(rental.user.idx.eq(users.getIdx()))
                .fetch();
    }
}

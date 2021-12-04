package com.CRUD.test.domain.rentalbook.repository;

import com.CRUD.test.domain.rentalbook.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}

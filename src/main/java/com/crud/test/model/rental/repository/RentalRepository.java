package com.crud.test.model.rental.repository;

import com.crud.test.model.rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}

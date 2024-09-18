package com.example.back_end.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanList, Long> {
    Optional<LoanList> findByIsbnCode(String isbnCode);
}

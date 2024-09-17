package com.example.back_end.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanList, Long> {
}

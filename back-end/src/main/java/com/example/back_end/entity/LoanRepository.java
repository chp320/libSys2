package com.example.back_end.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanList, Long> {
    Optional<LoanList> findByIsbnCode(String isbnCode);

    // userid 와 sysdate between loan_date and return_date 에 부합하는 목록 조회
    List<LoanList> findByUserIDAndLoanDateAndReturnDate(
            String userID,
            LocalDateTime currentDate,
            LocalDateTime currentDate2
    );

    // get the data between from-date and to-date.
    List<LoanList> findByUserIDAndLoanDateLessThanEqualAndReturnDateGreaterThanEqualAndBookStatusEquals(
            String userID,
            LocalDateTime now1,
            LocalDateTime now2,
            String bookStatus
    );

//    // update bookStatus, returnDate via isbnCode
//    List<LoanList> findByIsbnCodeAndBookStatusAndReturnDate(
//            String isbnCode,
//            String bookStatus,
//            LocalDateTime currentDate
//    );

    Optional<LoanList> findByIsbnCodeAndBookStatus(
            String isbnCode,
            String bookStatus
    );
}

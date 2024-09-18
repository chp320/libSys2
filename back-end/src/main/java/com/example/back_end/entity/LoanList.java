package com.example.back_end.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 회원 도서 대출 목록을 관리한다.
 * - 회원번호(id), isbn코드(isbn), 책제목(title), 대출일자(loanDate), 반납예정일자(returnDate)
 */
@Getter
@Setter
@Entity
public class LoanList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String isbnCode;

    @Column
    private String title;

    @Column(nullable = false)
    private LocalDateTime loanDate;

    @Column(nullable = false)
    private LocalDateTime returnDate;
}

package com.example.back_end.service;

import com.example.back_end.entity.LoanList;
import com.example.back_end.entity.LoanRepository;
import com.example.back_end.exception.DuplicateRecordException;
import com.example.back_end.exception.UniqueConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // creating list
    public List<LoanList> createLoans(List<LoanList> loanLists) {
        // 중복 저장 시 발생하는 unique 에러 처리 위한 코드 수정
//        return loanRepository.saveAll(loanLists);

//        for(LoanList loanList : loanLists){
//            try {
//                loanRepository.save(loanList);
//            } catch (DataIntegrityViolationException e) {
//                System.out.println("Unique constraint violated: " + e.getMessage());
//                throw new UniqueConstraintException("Unique constraint violation: " + e.getMessage());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

//        insert 수행 전, 이미 존재하는지 확인
        for (LoanList loanList : loanLists) {
            loanList.getLoanDate().plusHours(9L);
            loanList.getReturnDate().plusHours(9L);
//            Optional<LoanList> existingLoan = loanRepository.findByIsbnCode(loanList.getIsbnCode());
            Optional<LoanList> existingLoan = loanRepository.findByIsbnCodeAndUserID(loanList.getIsbnCode(), loanList.getUserID());

            if(existingLoan.isPresent()){
                throw new DuplicateRecordException("ISBN Code " + loanList.getIsbnCode() + " already exists.");
            }

            try {
                loanRepository.save(loanList);
            } catch (DataIntegrityViolationException e) {
                throw new UniqueConstraintException("Unique constraint violation: " + e.getMessage());
            }
        }

        return loanLists;
    }

    // reading list (userid, loan_date, return_date)
    public List<LoanList> getLoanList(String userID, LocalDateTime currentDate) {
        return loanRepository.findByUserIDAndLoanDateAndReturnDate(userID, currentDate, currentDate);
    }

    public List<LoanList> getLoanLists(String userID, String bookStatus) {
        LocalDateTime now = LocalDateTime.now();
        return loanRepository.findByUserIDAndLoanDateLessThanEqualAndReturnDateGreaterThanEqualAndBookStatusEquals(userID, now, now, bookStatus);
    }

//    public List<LoanList> setReturnBook(String isbnCode, String bookStatus, LocalDateTime returnDate) {
//        return loanRepository.findByIsbnCodeAndBookStatusAndReturnDate(isbnCode, bookStatus, returnDate);
//    }

//    public LoanList returnBook(String isbnCode, String bookStatus, LocalDateTime returnDate) {
//        // find the loan record by ISBN code and ensure it's currently borrowed
//        Optional<LoanList> optionalLoanList = loanRepository.findByIsbnCodeAndBookStatusAndReturnDate(isbnCode, bookStatus, returnDate);
//    }

    public boolean updateBookStatusAndReturnDate(String isbnCode,
                                                 String bookStatus) {
        Optional<LoanList> loanOptional = loanRepository.findByIsbnCodeAndBookStatus(isbnCode, bookStatus);

        // 데이터 존재하는 경우 업데이트 처리!
        if(loanOptional.isPresent()){
            LoanList loanList = loanOptional.get();
            loanList.setBookStatus("N");
            loanList.setReturnDate(LocalDateTime.now().plusHours(9L));
//            loanList.setReturnDate(currentDate);
            loanRepository.save(loanList);
            return true;
        }
        return false;
    }
}

package com.example.back_end.service;

import com.example.back_end.entity.LoanList;
import com.example.back_end.entity.LoanRepository;
import com.example.back_end.exception.DuplicateRecordException;
import com.example.back_end.exception.UniqueConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
            Optional<LoanList> existingLoan = loanRepository.findByIsbnCode(loanList.getIsbnCode());

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
}

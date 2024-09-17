package com.example.back_end.service;

import com.example.back_end.entity.LoanList;
import com.example.back_end.entity.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // creating list
    public List<LoanList> createLoans(List<LoanList> loanLists) {
        return loanRepository.saveAll(loanLists);
    }
}

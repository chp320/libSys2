package com.example.back_end.controller;

import com.example.back_end.entity.LoanList;
import com.example.back_end.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/loan/register")
    public List<LoanList> createLoans(@RequestBody List<LoanList> loanList){
        System.out.println("(createLoans) UserID: " + loanList.get(0).getUserID()
                            + ", userName: " + loanList.get(0).getUserName()
                            + ", isbnCode: " + loanList.get(0).getIsbnCode()
                            + ", title: " + loanList.get(0).getTitle()
                            + ", loanDate: " + loanList.get(0).getLoanDate()
                            + ", returnDate: " + loanList.get(0).getReturnDate()
        );
        return loanService.createLoans(loanList);
    }
}

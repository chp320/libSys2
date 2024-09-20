package com.example.back_end.controller;

import com.example.back_end.entity.LoanList;
import com.example.back_end.exception.DuplicateRecordException;
import com.example.back_end.exception.UniqueConstraintException;
import com.example.back_end.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/loan/register")
//    public List<LoanList> createLoans(@RequestBody List<LoanList> loanList){
    public ResponseEntity<?> createLoans(@RequestBody List<LoanList> loanList) {
        System.out.println("(createLoans) UserID: " + loanList.get(0).getUserID()
                + ", userName: " + loanList.get(0).getUserName()
                + ", isbnCode: " + loanList.get(0).getIsbnCode()
                + ", title: " + loanList.get(0).getTitle()
                + ", loanDate: " + loanList.get(0).getLoanDate()
                + ", returnDate: " + loanList.get(0).getReturnDate()
        );
        // insert 중복 오류 발생 처리 위함
//        return loanService.createLoans(loanList);

        try {
            List<LoanList> savedLoans = loanService.createLoans(loanList);
            //        return ResponseEntity.ok(savedLoans.get(0));
            return ResponseEntity.ok(savedLoans);
        } catch (DuplicateRecordException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (UniqueConstraintException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @GetMapping("/loan/getList")
    public List<LoanList> getLoanList(@RequestParam String userID) {
        // get current date-time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // call the service to get the list of loans
        return loanService.getLoanList(userID, currentDateTime);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/loan/getLists")
    public ResponseEntity<?> getLoanLists(@RequestParam String userID) {
        List<LoanList> loanLists = loanService.getLoanLists(userID);
        return ResponseEntity.ok(loanLists);
    }
}

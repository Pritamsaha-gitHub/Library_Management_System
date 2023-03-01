package com.example.librarymanagementsystem.Controller;

import com.example.librarymanagementsystem.DTOs.IssueBookRequestDto;
import com.example.librarymanagementsystem.DTOs.ReturnBookRequestDto;
import com.example.librarymanagementsystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity<String> issueBook(@RequestBody()IssueBookRequestDto issueBookRequestDto){
        try{
            String result=transactionService.issueBook(issueBookRequestDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String result=e.getMessage();
            return new ResponseEntity<>(result, HttpStatus.INSUFFICIENT_STORAGE);
        }
    }
    @GetMapping
    public String getTransactionEntry(@RequestParam("bookId") Integer bookId,@RequestParam("cardId") Integer cardId){
        return transactionService.getTransaction(bookId,cardId);
    }
    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(@RequestBody()ReturnBookRequestDto returnBookRequestDto){
        try {
            String result=transactionService.returnBook(returnBookRequestDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String result=e.getMessage();
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
    }


}

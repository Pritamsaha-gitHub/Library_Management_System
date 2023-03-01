package com.example.librarymanagementsystem.Controller;

import com.example.librarymanagementsystem.DTOs.BookEntryDto;
import com.example.librarymanagementsystem.Models.Book;
import com.example.librarymanagementsystem.ResponseDtos.BookResponseDto;
import com.example.librarymanagementsystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add_book")
    public ResponseEntity<String> addBook(@RequestBody() BookEntryDto bookEntryDto){
        String result=bookService.addBook(bookEntryDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PutMapping("/update_pages")
    public ResponseEntity<String>updateBookPages(@RequestParam("id") int bookId,int pages){
        try{
            String result=bookService.updateBookPages(bookId,pages);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch(Exception e){
            String result=e.getMessage();
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get_Book_By_id")
    public ResponseEntity<BookResponseDto> getBookById(@RequestParam("id") int bookId){
        try{
            BookResponseDto result=bookService.getBookById(bookId);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}

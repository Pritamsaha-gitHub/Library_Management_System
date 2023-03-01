package com.example.librarymanagementsystem.Controller;

import com.example.librarymanagementsystem.DTOs.AuthorEntryDto;
import com.example.librarymanagementsystem.Models.Author;
import com.example.librarymanagementsystem.ResponseDtos.AuthorResponseDto;
import com.example.librarymanagementsystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add_author")
    public ResponseEntity<String> addAuthor(@RequestBody AuthorEntryDto authorEntryDto){

            String ans=authorService.addAuthor(authorEntryDto);
            return new ResponseEntity<>(ans, HttpStatus.FOUND);
    }
    @GetMapping("/get_author_by_id")
    public ResponseEntity<AuthorResponseDto> getAuthor(@RequestParam("id") int authorid){
        try {
            AuthorResponseDto author=authorService.getAuthor(authorid);
            return new ResponseEntity<>(author,HttpStatus.FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all_Books_By_Author")
    public ResponseEntity<List<String>> getBookList(@RequestParam("id") int authorId){
        try {
            List<String> bookList=authorService.getBookList(authorId);
            return new ResponseEntity<>(bookList,HttpStatus.FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/author_rating")
    public ResponseEntity<Double> authorRating(@RequestParam("id") int authorId){
        try {
            double result=authorService.authorRating(authorId);
            return new ResponseEntity<>(result,HttpStatus.FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}

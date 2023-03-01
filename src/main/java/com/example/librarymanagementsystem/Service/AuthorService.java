package com.example.librarymanagementsystem.Service;

import com.example.librarymanagementsystem.Converters.AuthorConverter;
import com.example.librarymanagementsystem.DTOs.AuthorEntryDto;
import com.example.librarymanagementsystem.Models.Author;
import com.example.librarymanagementsystem.Models.Book;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import com.example.librarymanagementsystem.ResponseDtos.AuthorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AuthorEntryDto authorEntryDto){
        Author author= AuthorConverter.EntryToDto(authorEntryDto);
        authorRepository.save(author);
        return "new Author sucessfully register";
    }
    public AuthorResponseDto getAuthor(int authorid){
        Author author=authorRepository.findById(authorid).get();
        AuthorResponseDto authorResponseDto=AuthorConverter.entityToResponse(author);
        return authorResponseDto;
    }
    public List<String> getBookList(int authorId) throws Exception{
        Author author=authorRepository.findById(authorId).get();
        List<String>bookList=new ArrayList<>();
        List<Book> booksWritten=author.getBooksWritten();
        for(Book b:booksWritten){
            bookList.add(b.getName());
        }
        return bookList;
    }
    public double authorRating(int authorId){
        Author author=authorRepository.findById(authorId).get();
        return author.getRating();
    }
}

package com.example.librarymanagementsystem.Service;

import com.example.librarymanagementsystem.Converters.BookConverter;
import com.example.librarymanagementsystem.DTOs.BookEntryDto;
import com.example.librarymanagementsystem.Models.Author;
import com.example.librarymanagementsystem.Models.Book;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.ResponseDtos.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookEntryDto bookEntryDto){
        //getting authorId from Dto
        int authorId=bookEntryDto.getAuthorId();
        Author author=authorRepository.findById(authorId).get();

        //creating new book
        Book book= BookConverter.DtoToEntity(bookEntryDto);
        book.setAuthor(author);

        //updating BookList
        List<Book>currentBookList=author.getBooksWritten();
        currentBookList.add(book);
        authorRepository.save(author);
        return "Book Sucessfully added";
    }
    public String updateBookPages(int bookId,int pages) throws Exception{
        Book book=bookRepository.findById(bookId).get();

        book.setPages(pages);
        bookRepository.save(book);
        return "Book pages Sucessfully updated";
    }
    public BookResponseDto getBookById(int bookId) throws Exception{
        Book bookEntity=bookRepository.findById(bookId).get();
        BookResponseDto book=BookConverter.entityToResponseDto(bookEntity);
        return book;
    }
}

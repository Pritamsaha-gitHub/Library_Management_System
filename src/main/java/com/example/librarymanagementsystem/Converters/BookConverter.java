package com.example.librarymanagementsystem.Converters;

import com.example.librarymanagementsystem.DTOs.BookEntryDto;
import com.example.librarymanagementsystem.Models.Author;
import com.example.librarymanagementsystem.Models.Book;
import com.example.librarymanagementsystem.ResponseDtos.BookResponseDto;

public class BookConverter {
    public static Book DtoToEntity(BookEntryDto bookEntryDto){
        Book book=Book.builder()
                .name(bookEntryDto.getName())
                .pages(bookEntryDto.getPages())
                .genre(bookEntryDto.getGenre())
                .issued(false)
                .build();
        return book;
    }
    public static BookResponseDto entityToResponseDto(Book book){
        Author author=book.getAuthor();
        BookResponseDto bookResponseDto=BookResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .pages(book.getPages())
                .genre(book.getGenre())
                .authorName(author.getName())
                .build();
        return bookResponseDto;
    }
}

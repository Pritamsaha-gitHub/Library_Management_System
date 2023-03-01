package com.example.librarymanagementsystem.Converters;

import com.example.librarymanagementsystem.DTOs.AuthorEntryDto;
import com.example.librarymanagementsystem.Models.Author;
import com.example.librarymanagementsystem.ResponseDtos.AuthorResponseDto;

public class AuthorConverter {
    public static Author EntryToDto(AuthorEntryDto authorEntryDto){
        Author author=Author.builder()
                .name(authorEntryDto.getName())
                .age(authorEntryDto.getAge())
                .country(authorEntryDto.getCountry())
                .rating(authorEntryDto.getRating())
                .build();
        return author;
    }
    public static AuthorResponseDto entityToResponse(Author author){
        AuthorResponseDto authorResponseDto= AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .age(author.getAge())
                .country(author.getCountry())
                .rating(author.getRating())
                .build();
        return authorResponseDto;
    }
}

package com.example.librarymanagementsystem.DTOs;

import com.example.librarymanagementsystem.Enums.Genre;
import lombok.Data;

@Data
public class BookEntryDto {
    private String name;
    private int pages;
    private Genre genre;
    private int authorId;

}

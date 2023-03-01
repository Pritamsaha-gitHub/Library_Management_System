package com.example.librarymanagementsystem.ResponseDtos;

import com.example.librarymanagementsystem.Enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookResponseDto {
    private int id;
    private String name;
    private int pages;
    private Genre genre;
    private String authorName;
}

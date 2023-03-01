package com.example.librarymanagementsystem.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthorResponseDto {
    private int id;
    private String name;
    private int age;
    private String country;
    private double rating;
}

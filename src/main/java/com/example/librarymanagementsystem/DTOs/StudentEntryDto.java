package com.example.librarymanagementsystem.DTOs;

import javax.persistence.Column;
import lombok.Data;

@Data
public class StudentEntryDto {
    private String name;

    private String email;

    private String mobNo;

    private int age;

    private String country;
}

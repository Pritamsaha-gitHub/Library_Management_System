package com.example.librarymanagementsystem.ResponseDtos;

import com.example.librarymanagementsystem.Enums.Cardstatus;
import jakarta.persistence.Column;
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
public class StudentResponseDto {
    private int id;

    private String name;

    private String email;

    private String mobNo;

    private int age;

    private String country;

    private int cardId;
    @Enumerated(value = EnumType.STRING)
    private Cardstatus cardstatus;
}

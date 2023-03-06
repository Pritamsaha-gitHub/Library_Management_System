package com.example.librarymanagementsystem.ResponseDtos;

import com.example.librarymanagementsystem.Enums.Cardstatus;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

package com.example.librarymanagementsystem.DTOs;

import lombok.Data;

@Data
public class ReturnBookRequestDto {
    private String transactionId;
    private int amountPaid;
    private int daysCount;
}

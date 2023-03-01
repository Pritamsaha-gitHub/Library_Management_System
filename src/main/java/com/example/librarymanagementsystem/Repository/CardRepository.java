package com.example.librarymanagementsystem.Repository;

import com.example.librarymanagementsystem.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
}

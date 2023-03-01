package com.example.librarymanagementsystem.Models;

import com.example.librarymanagementsystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int pages;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private boolean issued;

    @ManyToOne
    @JoinColumn
    private Card card;

    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction>booksTransaction=new ArrayList<>();


}

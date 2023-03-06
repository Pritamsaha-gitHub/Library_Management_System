package com.example.librarymanagementsystem.Models;

import com.example.librarymanagementsystem.Enums.Cardstatus;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private Cardstatus cardstatus;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToOne
    @JoinColumn(nullable = false)
    Student student;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book>bookIssued=new ArrayList<>();

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();


}

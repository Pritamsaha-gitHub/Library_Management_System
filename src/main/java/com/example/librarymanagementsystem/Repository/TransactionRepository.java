package com.example.librarymanagementsystem.Repository;

import com.example.librarymanagementsystem.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    @Query(value = "select * from transaction where book_id=:bookId and card_id=:cardId and is_issue_operation=true",nativeQuery = true)
    List<Transaction>getTransactionforBookAndCard(int bookId,int cardId);

    @Query(value = "select * from transaction where transaction_id=:transactionId",nativeQuery = true)
    Transaction getTransactionByid(String transactionId);
}

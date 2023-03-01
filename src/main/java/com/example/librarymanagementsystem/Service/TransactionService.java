package com.example.librarymanagementsystem.Service;

import com.example.librarymanagementsystem.DTOs.IssueBookRequestDto;
import com.example.librarymanagementsystem.DTOs.ReturnBookRequestDto;
import com.example.librarymanagementsystem.Enums.Cardstatus;
import com.example.librarymanagementsystem.Enums.TransactionStatus;
import com.example.librarymanagementsystem.Models.Book;
import com.example.librarymanagementsystem.Models.Card;
import com.example.librarymanagementsystem.Models.Transaction;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;
    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{
        //changes in book part
        int bookId=issueBookRequestDto.getBookId();
        int cardId=issueBookRequestDto.getCardId();

        Book book=bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();

        //changes
        Transaction transaction=new Transaction();
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());//need to change fine structure

        if(book==null || book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }
        if (card==null || (card.getCardstatus() != Cardstatus.ACTIVATED)){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            throw new Exception("Card is not valid");
        }


        transaction.setTransactionStatus(TransactionStatus.SUCESS);
        book.setIssued(true);
        List<Transaction> currentTranactionList=book.getBooksTransaction();
        currentTranactionList.add(transaction);
        book.setBooksTransaction(currentTranactionList);

        List<Book>totalBooks=card.getBookIssued();
        totalBooks.add(book);
        card.setBookIssued(totalBooks);


        List<Transaction>currentList=card.getTransactionList();
        currentList.add(transaction);
        card.setTransactionList(currentList);
        cardRepository.save(card);
        return "book sucessfully given to student";


    }

    public String getTransaction(int bookId,int cardId){
        List<Transaction>transactionList=transactionRepository.getTransactionforBookAndCard(bookId,cardId);
        String transactionId=transactionList.get(0).getTransactionId();
        return transactionId;
    }
    //return books
    public String returnBook(ReturnBookRequestDto returnBookRequestDto) throws Exception{
        String transactionId=returnBookRequestDto.getTransactionId();
        Transaction transaction=transactionRepository.getTransactionByid(transactionId);
        Book book=transaction.getBook();
        Card card=transaction.getCard();

        Transaction newTransaction=new Transaction();
        newTransaction.setIssueOperation(false);
        newTransaction.setTransactionStatus(TransactionStatus.PENDING);
        newTransaction.setBook(book);
        newTransaction.setCard(card);
        newTransaction.setTransactionId(UUID.randomUUID().toString());
        if (book==null || book.isIssued()==false){
            newTransaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(newTransaction);
            throw new Exception("This Book is not booked");
        }
        if(card==null || (card.getCardstatus() != Cardstatus.ACTIVATED)){
            newTransaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(newTransaction);
            throw new Exception("Card is not active Please Active The Card");
        }
        //if Days Count is greater Than 15 then Rs.5 will count for each day
        int daysCount=returnBookRequestDto.getDaysCount();
        int collectedAmount= returnBookRequestDto.getAmountPaid();
        if(daysCount>15){
            int day=daysCount-15;
            int total=day * 5;
            if(total>collectedAmount){
                newTransaction.setTransactionStatus(TransactionStatus.FAILURE);
                transactionRepository.save(newTransaction);
                throw new Exception("Insufficient Balance");
            }
        }
        newTransaction.setTransactionStatus(TransactionStatus.SUCESS);
        book.setIssued(false);

        List<Transaction> currentTranactionList=book.getBooksTransaction();
        currentTranactionList.add(newTransaction);
        book.setBooksTransaction(currentTranactionList);

        List<Book>totalBooks=card.getBookIssued();
        totalBooks.remove(book);
        card.setBookIssued(totalBooks);
        cardRepository.save(card);
        return "Book Returned Sucessfully";
    }
}

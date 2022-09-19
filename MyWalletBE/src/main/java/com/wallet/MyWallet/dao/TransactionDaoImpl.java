package com.wallet.MyWallet.dao;

import com.wallet.MyWallet.entity.Transaction;
import com.wallet.MyWallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDaoImpl implements TransactionDao{

    private final TransactionRepository repository;

    @Autowired
    public TransactionDaoImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactionsByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<Transaction> getAllTransactionsByUserEmail(String userEmail) {
        return repository.findAllByUserEmail(userEmail);
    }

    @Override
    public void deleteAllTransactionsByUserId(Long userId) {

    }
}

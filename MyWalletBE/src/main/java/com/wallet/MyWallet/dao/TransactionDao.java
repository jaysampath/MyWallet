package com.wallet.MyWallet.dao;

import com.wallet.MyWallet.entity.Transaction;

import java.util.List;

public interface TransactionDao {

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> getAllTransactionsByUserId(Long userId);

    List<Transaction> getAllTransactionsByUserEmail(String userEmail);

}

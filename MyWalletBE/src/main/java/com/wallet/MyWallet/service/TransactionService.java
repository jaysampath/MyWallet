package com.wallet.MyWallet.service;

import com.wallet.MyWallet.dao.AccountDao;
import com.wallet.MyWallet.dao.TransactionDao;
import com.wallet.MyWallet.entity.Transaction;
import com.wallet.MyWallet.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AccountDao accountDao;

    public List<Transaction> getTransactionsByUserId(Long userId){
        if(accountDao.getUserById(userId) == null)
            throw new AccountNotFoundException("No account found with the userId " + userId);

        return transactionDao.getAllTransactionsByUserId(userId);
    }

    public List<Transaction> getTransactionsByUserEmail(String userEmail){
        if(accountDao.getUserByEmail(userEmail) == null)
            throw new AccountNotFoundException("No account found with the userId " + userEmail);

        return transactionDao.getAllTransactionsByUserEmail(userEmail);
    }
}

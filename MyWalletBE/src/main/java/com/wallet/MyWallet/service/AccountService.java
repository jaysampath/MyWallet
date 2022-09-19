package com.wallet.MyWallet.service;

import com.wallet.MyWallet.commons.response.BalanceResponse;
import com.wallet.MyWallet.commons.response.TransactionResponse;
import com.wallet.MyWallet.commons.enums.TransactionStatus;
import com.wallet.MyWallet.dao.AccountDao;
import com.wallet.MyWallet.entity.Account;
import com.wallet.MyWallet.exception.IncorrectTransactionPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public TransactionResponse addMoneyToWallet(Long userId, String userEmail, String inputTransactionPassword, int amount){
        validateTransactionPassword(userId, inputTransactionPassword);
        int currentBalance = accountDao.addMoneyToWallet(userId, userEmail, amount);
        return new TransactionResponse(TransactionStatus.SUCCESS, currentBalance);
    }

    public TransactionResponse withdrawMoneyFromWallet(Long userId, String userEmail, String inputTransactionPassword,  int amount){
        validateTransactionPassword(userId, inputTransactionPassword);
        int currentBalance = accountDao.withdrawMoneyFromWallet(userId, userEmail, amount);
        return new TransactionResponse(TransactionStatus.SUCCESS, currentBalance);
    }

    public BalanceResponse getAccountBalance(Long userId, String userEmail){
        return accountDao.getBalance(userId, userEmail);
    }

    public void validateTransactionPassword(Long userId, String inputPassword){
        if(!accountDao.validateTransactionPassword(userId, inputPassword)){
            throw new IncorrectTransactionPasswordException("TransactionPassword is incorrect");
        }
    }

    public Account updateTransactionPassword(String userEmail, String newTransactionPassword){
        return accountDao.updateTransactionPassword(userEmail, newTransactionPassword);
    }

}

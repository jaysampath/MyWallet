package com.wallet.MyWallet.service;

import com.wallet.MyWallet.dao.AccountDao;
import com.wallet.MyWallet.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AccountDao accountDao;

    public Account register(Account account){
        return accountDao.accountRegister(account);
    }

    public Account getAccountById(Long id){
        return accountDao.getUserById(id);
    }

    public Account getAccountByEmail(String email){
        return accountDao.getUserByEmail(email);
    }

    public boolean isUserExists(String email){
        return accountDao.isUserExists(email);
    }

    public Account updateAccountPassword(String email, String newAccountPassword){
        return accountDao.updateAccountPassword(email, newAccountPassword);
    }
}

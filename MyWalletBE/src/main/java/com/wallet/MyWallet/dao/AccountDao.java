package com.wallet.MyWallet.dao;

import com.wallet.MyWallet.commons.response.BalanceResponse;
import com.wallet.MyWallet.entity.Account;

public interface AccountDao {

    Account accountRegister(Account user);

    boolean isUserExists(String userEmail);

    boolean isUserAuthenticated(String userEmail, String password);

    Account getUserByEmail(String email);

    Account getUserById(Long id);

    int addMoneyToWallet(long userId, String userEmail, int creditAmount);

    int withdrawMoneyFromWallet(long userId, String userEmail, int debitAmount);

    BalanceResponse getBalance(Long userId, String userEmail);

    boolean validateTransactionPassword(Long userId, String givenPassword);

    Account updateAccountPassword(String userEmail, String newAccountPassword);

    Account updateTransactionPassword(String userEmail, String newTransactionPassword);
}

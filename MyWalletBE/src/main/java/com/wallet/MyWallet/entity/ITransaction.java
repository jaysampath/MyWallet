package com.wallet.MyWallet.entity;

import com.wallet.MyWallet.commons.enums.TransactionStatus;
import com.wallet.MyWallet.commons.enums.TransactionType;

public interface ITransaction {

    String TABLE_NAME = "transaction";
    String ID = "transactionId";
    String USER_EMAIL = "userEmail";

    String USER_ID = "userId";
    String TRANSACTION_TYPE = "transactionType";
    String TRANSACTION_STATUS = "transactionStatus";
    String TRANSACTION_TIME = "time";
    String TRANSACTION_AMOUNT = "amount";
    String CURRENT_BALANCE = "currentBalance";

    void setId(long id);
    void setUserEmail(String userEmail);

    void setUserId(Long userId);
    void setTransactionType(TransactionType transactionType);
    void setTransactionTime(long transactionTime);
    void setTransactionStatus(TransactionStatus transactionStatus);
    void setTransactionAmount(int amount);
    void setCurrentBalance(int balance);

    long getId();
    TransactionStatus getTransactionStatus();
    TransactionType getTransactionType();
    Long getTime();
    String getUserEmail();
    Long getUserId();
    int getTransactionAmount();
    int getCurrentBalance();

}

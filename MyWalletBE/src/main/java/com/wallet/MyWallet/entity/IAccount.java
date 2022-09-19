package com.wallet.MyWallet.entity;

public interface IAccount {

    String TABLE_NAME = "accounts";
    String ID = "id";
    String EMAIL = "email";

    String USERNAME = "username";

    String PREFERRED_USERNAME = "prettyName";

    String PASSWORD = "password";
    String TYPE = "type";
    String STATUS = "status";
    String TRANSACTION_PASSWORD = "transactionPassword";
    String BALANCE = "balance";

    void setId(Long id);
    void setEmail(String email);
    void setPassword(String password);
    void setUsername(String username);
    void setPreferredUsername(String prettyName);
    void setType(String type);
    void setStatus(String status);
    void setTransactionPassword(String transactionPassword);
    void setBalance(int balance);

    Long getId();
    String getEmail();
    String getPassword();
    String getUsername();
    String getPreferredUsername();
    String getType();
    String getStatus();
    String getTransactionPassword();
    int getBalance();
}

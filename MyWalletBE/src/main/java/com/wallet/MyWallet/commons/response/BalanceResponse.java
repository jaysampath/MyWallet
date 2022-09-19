package com.wallet.MyWallet.commons.response;


public class BalanceResponse {

    private Long userId;
    private String userEmail;
    private int balance;

    public BalanceResponse(Long userId, String userEmail, int balance) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getBalance() {
        return balance;
    }
}

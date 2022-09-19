package com.wallet.MyWallet.commons.response;

import com.wallet.MyWallet.commons.enums.TransactionStatus;

public class TransactionResponse {
    TransactionStatus status;
    int currentBalance;

    public TransactionResponse(TransactionStatus status, int currentBalance) {
        this.status = status;
        this.currentBalance = currentBalance;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }
}

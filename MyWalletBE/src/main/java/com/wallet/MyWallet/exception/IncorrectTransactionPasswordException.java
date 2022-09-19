package com.wallet.MyWallet.exception;

public class IncorrectTransactionPasswordException extends RuntimeException{

    public IncorrectTransactionPasswordException(String message){
        super(message);
    }
}

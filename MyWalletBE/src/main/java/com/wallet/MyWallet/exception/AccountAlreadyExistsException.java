package com.wallet.MyWallet.exception;

public class AccountAlreadyExistsException extends RuntimeException{

    public AccountAlreadyExistsException(String message){
        super(message);
    }
}

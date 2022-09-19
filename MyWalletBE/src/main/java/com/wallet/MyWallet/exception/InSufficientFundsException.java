package com.wallet.MyWallet.exception;

public class InSufficientFundsException extends  RuntimeException{

    public InSufficientFundsException(String message){
        super(message);
    }
}

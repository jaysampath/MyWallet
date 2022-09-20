package com.wallet.MyWallet.commons.validation;

import com.wallet.MyWallet.commons.enums.TransactionType;
import com.wallet.MyWallet.commons.request.LoginRequest;
import com.wallet.MyWallet.commons.request.SignupRequest;
import com.wallet.MyWallet.commons.request.TransactionRequest;
import com.wallet.MyWallet.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserInputValidator {

    public boolean validateEmail(String userEmail){
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.matches(regexPattern, userEmail);
    }

    public boolean validateStringInput(String s){
        return s != null && !s.isEmpty();
    }

    public boolean validateUsername(String username){
        return username != null && username.length() > 3;
    }

    public boolean validatePasswordLength(String password){
        return password != null && password.length()>=6;
    }

    public boolean validateUserId(Long userId){
        return userId != null && userId > 0;
    }

    public boolean validateTransactionAmount(int amount){
        return amount >10 && amount < 10000;
    }

    public boolean validateTransactionType(String type){
        return type.equals(TransactionType.CREDIT.name()) || type.equals(TransactionType.DEBIT.name());
    }

    public void validateSignupRequest(SignupRequest signupRequest) {
        if(!this.validateEmail(signupRequest.getUserEmail()))
            throw new InvalidInputException("Invalid email -" + signupRequest.getUserEmail());

        if(!this.validateUsername(signupRequest.getUsername()))
            throw new InvalidInputException("username must not be null or blank or less than three characters");

        if(!this.validatePasswordLength(signupRequest.getAccountPassword()))
            throw new InvalidInputException("Password should contain atleast 6 characters");

        if(!this.validatePasswordLength(signupRequest.getTransactionPassword()))
            throw new InvalidInputException("Transaction password should contain atleast 6 characters");
    }

    public void validateLoginRequest(LoginRequest loginRequest){
        if(!this.validateEmail(loginRequest.getEmail()))
            throw new InvalidInputException("Invalid email -" + loginRequest.getEmail());

        if(!this.validatePasswordLength(loginRequest.getPassword()))
            throw new InvalidInputException("Password should contain atleast 6 characters");
    }

    public void validateTransactionRequest(TransactionRequest transactionRequest){
        if(!this.validateEmail(transactionRequest.getUserEmail()))
            throw new InvalidInputException("Invalid email -" + transactionRequest.getUserEmail());

        if(!this.validatePasswordLength(transactionRequest.getTransactionPassword()))
            throw new InvalidInputException("Transaction password should contain atleast 6 characters");

        if(!this.validateUserId(transactionRequest.getUserId()))
            throw new InvalidInputException("Invalid userId -" +transactionRequest.getUserId());

        if(!this.validateTransactionAmount(transactionRequest.getAmount()))
            throw new InvalidInputException("Transaction amount should be greater than 10 and less than 10,000");

        if(!this.validateTransactionType(transactionRequest.getTransactionType().name()))
            throw new InvalidInputException("Invalid Transaction type - "+transactionRequest.getTransactionType().name());

    }
}

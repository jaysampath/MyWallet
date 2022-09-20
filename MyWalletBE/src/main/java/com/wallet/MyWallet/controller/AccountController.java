package com.wallet.MyWallet.controller;

import com.wallet.MyWallet.commons.response.BalanceResponse;
import com.wallet.MyWallet.commons.request.TransactionRequest;
import com.wallet.MyWallet.commons.response.TransactionResponse;
import com.wallet.MyWallet.commons.validation.UserInputValidator;
import com.wallet.MyWallet.exception.InvalidInputException;
import com.wallet.MyWallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserInputValidator validator;

    @GetMapping("/balance")
    public BalanceResponse getBalance(@RequestParam Long userId, @RequestParam String userEmail){
        if(!validator.validateEmail(userEmail) || !validator.validateUserId(userId))
            throw new InvalidInputException("Invalid userId or userEmail");

        return accountService.getAccountBalance(userId, userEmail);
    }

    @PostMapping("/credit")
    public TransactionResponse addMoneyToWallet(@RequestBody TransactionRequest transactionRequest){
        validator.validateTransactionRequest(transactionRequest);

        return accountService.addMoneyToWallet(transactionRequest.getUserId(), transactionRequest.getUserEmail(), transactionRequest.getTransactionPassword(),transactionRequest.getAmount());
    }

    @PostMapping("/debit")
    public TransactionResponse withdrawMoneyFromWallet(@RequestBody TransactionRequest transactionRequest){
        validator.validateTransactionRequest(transactionRequest);

        return accountService.withdrawMoneyFromWallet(transactionRequest.getUserId(), transactionRequest.getUserEmail(), transactionRequest.getTransactionPassword(), transactionRequest.getAmount());
    }

    @PostMapping("/update/transactionPassword")
    public ResponseEntity updateTransactionPassword(@RequestParam  String userEmail, @RequestParam  String newTransactionPassword){

        if(!validator.validateEmail(userEmail) || !validator.validatePasswordLength(newTransactionPassword))
            throw new InvalidInputException("email should be valid and password should contain atleast 6 characters.");

        accountService.updateTransactionPassword(userEmail, newTransactionPassword);
        return ResponseEntity.ok().build();
    }
}

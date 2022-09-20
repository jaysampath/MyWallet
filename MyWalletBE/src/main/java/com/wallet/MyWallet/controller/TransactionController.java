package com.wallet.MyWallet.controller;

import com.wallet.MyWallet.commons.validation.UserInputValidator;
import com.wallet.MyWallet.entity.Transaction;
import com.wallet.MyWallet.exception.InvalidInputException;
import com.wallet.MyWallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserInputValidator validator;

    @GetMapping("/all/id")
    public List<Transaction> getTransactionsByUserId(@RequestParam Long userId){
        if(!validator.validateUserId(userId))
            throw new InvalidInputException("Invalid userId -"+userId);

        return transactionService.getTransactionsByUserId(userId);
    }

    @GetMapping("/all/email")
    public List<Transaction> getTransactionsByEmail(@RequestParam String userEmail){
        if(!validator.validateEmail(userEmail))
            throw new InvalidInputException("Invalid email -" +userEmail);

        return transactionService.getTransactionsByUserEmail(userEmail);
    }
}

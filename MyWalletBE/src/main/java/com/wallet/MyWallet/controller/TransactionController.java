package com.wallet.MyWallet.controller;

import com.wallet.MyWallet.entity.Transaction;
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

    @GetMapping("/all/id")
    public List<Transaction> getTransactionsByUserId(@RequestParam Long userId){
        return transactionService.getTransactionsByUserId(userId);
    }

    @GetMapping("/all/email")
    public List<Transaction> getTransactionsByEmail(@RequestParam String userEmail){
        return transactionService.getTransactionsByUserEmail(userEmail);
    }
}

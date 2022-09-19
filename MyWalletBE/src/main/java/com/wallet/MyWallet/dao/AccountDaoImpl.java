package com.wallet.MyWallet.dao;

import com.wallet.MyWallet.commons.response.BalanceResponse;
import com.wallet.MyWallet.commons.enums.TransactionStatus;
import com.wallet.MyWallet.commons.enums.TransactionType;
import com.wallet.MyWallet.entity.Account;
import com.wallet.MyWallet.entity.Transaction;
import com.wallet.MyWallet.exception.AccountNotFoundException;
import com.wallet.MyWallet.exception.InSufficientFundsException;
import com.wallet.MyWallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Lazy
@Component
public class AccountDaoImpl implements AccountDao{

    private final AccountRepository repository;
    private final TransactionDao transactionDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountDaoImpl(AccountRepository repository, TransactionDao transactionDao, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.transactionDao = transactionDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account accountRegister(Account account) {
        if(isUserExists(account.getEmail()))
            throw new RuntimeException("Provided email already registered. Please login using the same email");
        account.setBalance(0);
        account.setTransactionPassword(passwordEncoder.encode(account.getTransactionPassword()));
        return repository.save(account);
    }

    @Override
    public boolean isUserExists(String userEmail) {
        Account user = repository.findByEmail(userEmail);
        return user != null;
    }

    @Override
    public boolean isUserAuthenticated(String userEmail, String password) {
        if(!isUserExists(userEmail))
            throw new AccountNotFoundException("User not Found");

        Account user = repository.findByEmailAndPassword(userEmail, password);
        return user != null;
    }

    @Override
    public Account getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Account getUserById(Long id) {
        Optional<Account> optionalAccount = repository.findById(id);
        return optionalAccount.orElse(null);
    }

    private int getCurrentBalance(long userId){
        Account account = getUserById(userId);
        if(account==null)
            throw new AccountNotFoundException("No account found with userId "+userId);
        return account.getBalance();
    }

    private int processTransaction(long userId, int amount, TransactionType transactionType){
        Transaction transaction = null;

        synchronized (this){
            Account account = getUserById(userId);
            if(account==null)
                throw new AccountNotFoundException("No account found with userId "+userId);

            if(transactionType == TransactionType.CREDIT){
                account.setBalance(account.getBalance() + amount);
                transaction = new Transaction(account.getEmail(), account.getId(), TransactionType.CREDIT,
                        TransactionStatus.INPROCESS, System.currentTimeMillis(), amount, account.getBalance());
            }else {
                if(account.getBalance() - amount < 0){
                    transaction = new Transaction(account.getEmail(), account.getId(), TransactionType.DEBIT,
                            TransactionStatus.FAILURE, System.currentTimeMillis(), amount, account.getBalance());
                    transactionDao.saveTransaction(transaction);
                    throw new InSufficientFundsException("Transaction is failed due to InsufficientFunds");
                }
                account.setBalance(account.getBalance() - amount);
                transaction = new Transaction(account.getEmail(), account.getId(), TransactionType.DEBIT,
                        TransactionStatus.INPROCESS, System.currentTimeMillis(), amount, account.getBalance());
            }
            repository.save(account);
        }
        if(transaction!=null && transaction.getTransactionStatus() == TransactionStatus.INPROCESS){
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }
        transactionDao.saveTransaction(transaction);
        return transaction.getCurrentBalance();
    }

    @Override
    public int addMoneyToWallet(long userId, String userEmail, int creditAmount) {
       return this.processTransaction(userId, creditAmount, TransactionType.CREDIT);
    }

    @Override
    public int withdrawMoneyFromWallet(long userId, String userEmail, int debitAmount) {
       return this.processTransaction(userId, debitAmount, TransactionType.DEBIT);
    }

    @Override
    public BalanceResponse getBalance(Long userId, String userEmail) {
        Account account = this.getUserById(userId);
        if(account == null)
            return null;

        return new BalanceResponse(account.getId(), account.getEmail(), account.getBalance());
    }

    @Override
    public boolean validateTransactionPassword(Long userId, String givenPassword){
        Account account = this.getUserById(userId);
        return passwordEncoder.matches(givenPassword, account.getTransactionPassword());
    }

    @Override
    public Account updateAccountPassword(String userEmail, String newAccountPassword){
        Account account = this.getUserByEmail(userEmail);
        account.setPassword(passwordEncoder.encode(newAccountPassword));
        return repository.save(account);
    }

    @Override
    public Account updateTransactionPassword(String userEmail, String newTransactionPassword){
        Account account = this.getUserByEmail(userEmail);
        account.setTransactionPassword(passwordEncoder.encode(newTransactionPassword));
        return repository.save(account);
    }
}

package com.wallet.MyWallet.commons;

import com.wallet.MyWallet.entity.Account;
import com.wallet.MyWallet.exception.AccountNotFoundException;
import com.wallet.MyWallet.repository.AccountRepository;
import com.wallet.MyWallet.service.AccountService;
import com.wallet.MyWallet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = repository.findByEmail(email);
        if(account==null)
            throw new AccountNotFoundException("Not a registered user");
        return accountToUserDetails(account);
    }

    private UserDetails accountToUserDetails(Account account){
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(account.getUsername());
        userDetails.setEmail(account.getEmail());
        userDetails.setPassword(account.getPassword());
        userDetails.setId(account.getId());
        return userDetails;
    }
}

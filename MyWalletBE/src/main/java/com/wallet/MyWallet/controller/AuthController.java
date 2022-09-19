package com.wallet.MyWallet.controller;

import com.wallet.MyWallet.commons.request.LoginRequest;
import com.wallet.MyWallet.commons.response.SuccessfulLoginResponse;
import com.wallet.MyWallet.entity.Account;
import com.wallet.MyWallet.exception.AccountAlreadyExistsException;
import com.wallet.MyWallet.exception.AccountNotFoundException;
import com.wallet.MyWallet.exception.JWTException;
import com.wallet.MyWallet.service.AuthService;
import com.wallet.MyWallet.service.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public SuccessfulLoginResponse authenticateUser(@RequestBody LoginRequest loginRequest){

        if(!authService.isUserExists(loginRequest.getEmail())){
            throw new AccountNotFoundException("Not a register user!");
        }

       String token = authenticateAndGetToken(loginRequest.getEmail(), loginRequest.getPassword());
        Account account = authService.getAccountByEmail(loginRequest.getEmail());
       return new SuccessfulLoginResponse(account.getId(), account.getEmail(), token, account.getUsername(),
               account.getPreferredUsername(), account.getStatus(), account.getType(), account.getBalance());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public SuccessfulLoginResponse registerUser(@RequestBody Account account){
        if(authService.isUserExists(account.getEmail())){
            throw new AccountAlreadyExistsException("An account is already registered with the "+ account.getEmail() + " . Please login with the same");
        }

        String actualPassword = account.getPassword();
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Account savedAccount = authService.register(account);
        String token = authenticateAndGetToken(savedAccount.getEmail(), actualPassword);
        return new SuccessfulLoginResponse(savedAccount.getId(), savedAccount.getEmail(), token, savedAccount.getUsername(),
                savedAccount.getPreferredUsername(), savedAccount.getStatus(), savedAccount.getType(), savedAccount.getBalance());
    }

    @GetMapping("/account")
    public Account getAccountNyEmail(@RequestParam String email){
        return authService.getAccountByEmail(email);
    }

    @PostMapping("/validate")
    public SuccessfulLoginResponse validateTokenAndGetUserInformation(@RequestParam String token){
        boolean isValid = tokenProvider.validateToken(token);
        if(!isValid){
            throw new JWTException("Invalid token or token expired. Please login in");
        }
        Long userId = tokenProvider.getUserIdFromJWT(token);
        return mapAccountToLoginResponse(authService.getAccountById(userId), token);
    }

    @PostMapping("/update/password")
    public ResponseEntity updateAccountPassword(@RequestParam String userEmail, String newPassword){
        authService.updateAccountPassword(userEmail, newPassword);
        return ResponseEntity.ok().build();
    }

    private SuccessfulLoginResponse mapAccountToLoginResponse(Account account, String accessToken){
        return new SuccessfulLoginResponse(
                account.getId(), account.getEmail(), accessToken, account.getUsername(), account.getPreferredUsername(),
                account.getStatus(), account.getType(), account.getBalance()
        );
    }

    private String authenticateAndGetToken(String email, String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return tokenProvider.generateToken(authentication);
    }
}

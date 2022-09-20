package com.wallet.MyWallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleAccountNotFoundException(AccountNotFoundException exception){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(
              response, HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleAccountAlreadyExistsException(AccountAlreadyExistsException exception){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(
                response, HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleJwtException(JWTException exception){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(
                response, HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleInSufficientFundsException(InSufficientFundsException exception){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(
                response, HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleIncorrectTransactionPasswordException(IncorrectTransactionPasswordException exception){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(
                response, HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleInvalidInputException(InvalidInputException exception){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(
                response, HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(
                response, HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

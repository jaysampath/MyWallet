package com.wallet.MyWallet.commons.response;

public class SuccessfulLoginResponse {
    private Long userId;
    private String userEmail;
    private String accessToken;
    private String username;
    private String prettyName;
    private String accountStatus;
    private String accountType;
    private int currentBalance;

    public SuccessfulLoginResponse(Long userId, String userEmail, String accessToken, String username, String prettyName,
                                   String accountStatus, String accountType, int currentBalance) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.accessToken = accessToken;
        this.username = username;
        this.prettyName = prettyName;
        this.accountStatus = accountStatus;
        this.accountType = accountType;
        this.currentBalance = currentBalance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }
}

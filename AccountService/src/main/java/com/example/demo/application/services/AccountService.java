package com.example.demo.application.services;

import com.example.demo.apis.resources.*;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(AccountCreation request);

    AccountDetail getAccountById(String accountId);

    List<AccountDetail> getAccountsByUser(String userId);

    AccountTransferResponse transfer(AccountTransferRequest request);
}

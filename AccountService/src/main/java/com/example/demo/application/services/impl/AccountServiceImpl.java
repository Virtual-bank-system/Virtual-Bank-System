package com.example.demo.application.services.impl;

import com.example.demo.application.enums.AccountType;
import com.example.demo.application.enums.Status;
import com.example.demo.application.exceptions.AccountNotFoundException;
import com.example.demo.application.exceptions.InsufficientFundsException;
import com.example.demo.application.models.Account;
import com.example.demo.application.repos.AccountRepo;
import com.example.demo.application.services.AccountService;
import com.example.demo.apis.resources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepository;

    public AccountResponse createAccount(AccountCreation request) {
        Account account = new Account();
        account.setUser_id(request.getUserId());
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setAccount_type(AccountType.valueOf(request.getAccountType().toUpperCase()));
        account.setBalance(request.getInitialBalance());
        account.setStatus(Status.ACTIVE);
        account.setCreated_at(LocalDateTime.now());
        account.setUpdated_at(LocalDateTime.now());

        Account saved = accountRepository.save(account);
        return new AccountResponse(saved.getId(), saved.getAccountNumber(), "Account created successfully");
    }

    public AccountDetail getAccountById(String accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account with ID " + accountId + " not found.",
                        "NOT_FOUND",
                        404
                ));
        return mapToDetail(account);
    }

    public List<AccountDetail> getAccountsByUser(String userId) {
        List<Account> accounts = accountRepository.findAll().stream()
                .filter(a -> a.getUser_id().equals(userId))
                .toList();

        if (accounts.isEmpty()) {
            throw new AccountNotFoundException("No accounts found for user: " + userId, "NOT_FOUND", 404);
        }

        return accounts.stream().map(this::mapToDetail).collect(Collectors.toList());
    }

    public AccountTransferResponse transfer(AccountTransferRequest request) {
        Account from = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(AccountNotFoundException::new);

        Account to = accountRepository.findById(request.getToAccountId())
                .orElseThrow(AccountNotFoundException::new);

        if (from.getBalance() < request.getAmount()) {
            throw new InsufficientFundsException();
        }

        from.setBalance(from.getBalance() - request.getAmount());
        to.setBalance(to.getBalance() + request.getAmount());
        from.setUpdated_at(LocalDateTime.now());
        to.setUpdated_at(LocalDateTime.now());

        accountRepository.save(from);
        accountRepository.save(to);

        return new AccountTransferResponse("Accounts updated successfully.");
    }

    private AccountDetail mapToDetail(Account account) {
        AccountDetail detail = new AccountDetail();
        detail.setAccountId(account.getId());
        detail.setAccountNumber(account.getAccountNumber());
        detail.setBalance(account.getBalance());
        detail.setAccountType(account.getAccount_type().name());
        detail.setStatus(account.getStatus().name());
        return detail;
    }
}

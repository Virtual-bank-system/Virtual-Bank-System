package application.services.impl;

import apis.resources.*;
import application.enums.AccountType;
import application.enums.Status;
import application.exceptions.AccountNotFoundException;
import application.exceptions.InsufficientFundsException;
import application.models.Account;
import application.repos.AccountRepo;
import application.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepo accountRepository;

    public AccountResponse createAccount(AccountCreation request) {
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setUser_id(request.getUserId());
        account.setAccount_number(generateAccountNumber());
        account.setAccount_type(AccountType.valueOf(request.getAccountType().toUpperCase()));
        account.setBalance(request.getInitialBalance());
        account.setStatus(Status.ACTIVE);
        account.setCreated_at(LocalDateTime.now());
        account.setUpdated_at(LocalDateTime.now());

        Account saved = accountRepository.save(account);
        return new AccountResponse(saved.getId(), saved.getAccount_number(), "Account created successfully");
    }

    public AccountDetail getAccountById(String accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
        return mapToDetail(account);
    }

    public List<AccountDetail> getAccountsByUser(String userId) {
        List<Account> accounts = accountRepository.findAll().stream()
                .filter(a -> a.getUser_id().equals(userId))
                .toList();

        if (accounts.isEmpty()) {
            throw new AccountNotFoundException("No accounts found for user: " + userId, "ACCOUNT_NOT_FOUND", 404);
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

        return new AccountTransferResponse("Transfer successful");
    }

    private AccountDetail mapToDetail(Account account) {
        AccountDetail detail = new AccountDetail();
        detail.setAccountId(account.getId());
        detail.setAccountNumber(account.getAccount_number());
        detail.setBalance(account.getBalance());
        detail.setAccountType(account.getAccount_type().name().toLowerCase());
        detail.setStatus(account.getStatus().name().toLowerCase());
        return detail;
    }

    private String generateAccountNumber() {
        return UUID.randomUUID().toString();
    }
}

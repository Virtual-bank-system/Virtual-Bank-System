package application.services.impl;

import apis.resources.*;
import application.enums.AccountType;
import application.enums.Status;
import application.exceptions.AccountNotFoundException;
import application.exceptions.InsufficientFundsException;
import application.exceptions.InvalidAccountException;
import application.feign.UserClient;
import application.models.Account;
import application.repos.AccountRepo;
import application.services.AccountService;
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

    @Autowired
    private UserClient userClient;

    public AccountResponse createAccount(AccountCreation request) {
        try {
            userClient.getProfile(request.getUserId());
        } catch (Exception e) {
            throw new InvalidAccountException("User with ID " + request.getUserId() + " does not exist" , "NOT_FOUND", 404);
        }

        if(request.getInitialBalance()<0 || Double.isNaN(request.getInitialBalance()))
            throw new InvalidAccountException();

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

        System.out.println(request.getAmount());
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

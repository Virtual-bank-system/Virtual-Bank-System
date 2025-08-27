package apis.controllers;

import apis.resources.*;
import application.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountCreation request) {
        return ResponseEntity.status(201).body(accountService.createAccount(request));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDetail> getAccountById(@PathVariable String accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<AccountDetail>> getAccountsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(accountService.getAccountsByUser(userId));
    }

    @PutMapping("/transfer")
    public ResponseEntity<AccountTransferResponse> transfer(@Valid @RequestBody AccountTransferRequest request) {
        return ResponseEntity.ok(accountService.transfer(request));
    }
}


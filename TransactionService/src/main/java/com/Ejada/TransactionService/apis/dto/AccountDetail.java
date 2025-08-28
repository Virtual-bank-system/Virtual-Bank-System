package com.Ejada.TransactionService.apis.dto;

import lombok.Data;

@Data
public class AccountDetail {
    private String accountId;
    private String accountNumber;
    private double balance;
    private String accountType; // "debit" or "credit"
    private String status;      // "active" or "inactive"
}
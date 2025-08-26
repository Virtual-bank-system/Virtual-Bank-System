package com.example.demo.apis.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDetail {
    private String accountId;
    private String accountNumber;
    private double balance;
    private String accountType;
    private String status;
}

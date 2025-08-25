package com.example.demo.apis.resources;

import lombok.Data;

@Data
public class AccountDetail {
    private String accountId;
    private String accountNumber;
    private double balance;
    private String accountType;
    private String status;
}

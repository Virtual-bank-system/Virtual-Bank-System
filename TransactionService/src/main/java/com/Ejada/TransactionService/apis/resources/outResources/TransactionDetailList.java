package com.Ejada.TransactionService.apis.resources.outResources;

import lombok.Data;

import java.util.List;

@Data
public class TransactionDetailList {
    List<TransactionDetail> transaction_response_list;
}

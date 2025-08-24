package com.Ejada.TransactionService.apis.resources.outResources;

import lombok.Data;

import java.util.List;

@Data
public class TransactionHistoryListResponse {
    List<TransactionHistoryResponse> transaction_response_list;
}

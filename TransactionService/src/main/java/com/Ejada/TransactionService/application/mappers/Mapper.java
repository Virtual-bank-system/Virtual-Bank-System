package com.Ejada.TransactionService.application.mappers;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionHistoryResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionInitiationResponse;
import com.Ejada.TransactionService.application.feign.dto.AccountTransferRequest;
import com.Ejada.TransactionService.application.models.Transaction;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    TransactionHistoryResponse toTransactionResponse(Transaction transaction);

    List<TransactionHistoryResponse> toTransactionResponseList(List<Transaction> transactions);

    TransactionInitiationResponse toTransactionInitiationResponse(Transaction transaction);

    AccountTransferRequest toAccountTransferRequest(Transaction transaction);
}

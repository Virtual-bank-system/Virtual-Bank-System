package com.Ejada.TransactionService.application.mappers;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionDetail;
import com.Ejada.TransactionService.apis.resources.outResources.TransferResponse;
import com.Ejada.TransactionService.application.feign.dto.AccountTransferRequest;
import com.Ejada.TransactionService.application.models.Transaction;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    TransactionDetail toTransactionResponse(Transaction transaction);

    TransferResponse toTransferResponse(Transaction transaction);

}

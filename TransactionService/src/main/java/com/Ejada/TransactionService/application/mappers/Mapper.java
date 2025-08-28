package com.Ejada.TransactionService.application.mappers;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionDetail;
import com.Ejada.TransactionService.apis.resources.outResources.TransferResponse;
import com.Ejada.TransactionService.application.models.Transaction;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    TransactionDetail toTransactionDetail(Transaction transaction);

    TransferResponse toTransferResponse(Transaction transaction);
}

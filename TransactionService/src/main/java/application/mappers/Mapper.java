package application.mappers;

import apis.resources.outResources.TransactionDetail;
import apis.resources.outResources.TransferResponse;
import application.models.Transaction;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    TransactionDetail toTransactionDetail(Transaction transaction);

    TransferResponse toTransferResponse(Transaction transaction);
}

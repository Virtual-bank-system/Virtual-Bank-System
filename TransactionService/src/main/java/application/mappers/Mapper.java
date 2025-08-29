package application.mappers;

import apis.resources.outResources.TransactionDetail;
import apis.resources.outResources.TransferResponse;
import application.models.Transactions;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    TransactionDetail toTransactionDetail(Transactions transactions);

    TransferResponse toTransferResponse(Transactions transactions);
}

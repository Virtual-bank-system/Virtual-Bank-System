package application.mapper;

import apis.dto.AccountDetail;
import apis.dto.AccountWithTransactions;
import apis.dto.TransactionDetail;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    AccountWithTransactions toAccountWithTransactions(AccountDetail accountDetail, List<TransactionDetail> transactions);
}

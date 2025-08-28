package application.mapper;

import apis.dto.AccountDetail;
import apis.dto.AccountWithTransactions;
import apis.dto.TransactionDetail;
import apis.dto.TransactionDetailList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(
            target = "transactions",
            expression = "java(toTransactionDetailList(transactionDetails))"
    )

    AccountWithTransactions toAccountWithTransactions(AccountDetail account, List<TransactionDetail> transactionDetails);

    default TransactionDetailList toTransactionDetailList(List<TransactionDetail> transactionDetails) {
        TransactionDetailList list = new TransactionDetailList();
        list.setTransactionDetailList(transactionDetails);
        return list;
    }
}

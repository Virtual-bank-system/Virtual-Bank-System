package application.mapper;

import apis.resources.AccountDetail;
import apis.resources.AccountWithTransactions;
import apis.resources.TransactionDetail;
import apis.resources.TransactionDetailList;
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

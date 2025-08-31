package application.services.impl;

import apis.resources.outResources.TransactionDetail;
import apis.resources.outResources.TransactionDetailList;
import apis.resources.outResources.TransferResponse;
import application.enums.DeliveryStatus;
import application.enums.Status;
import application.exceptions.InsufficientFundsException;
import application.exceptions.FailedTransactionException;
import application.exceptions.NoTransactionListException;
import application.exceptions.AccountNotFoundException;
import application.exceptions.SameAccountTransferException;
import application.feignClients.AccountClient;
import apis.dto.AccountDetail;
import apis.dto.AccountTransferRequest;
import application.mappers.Mapper;
import application.models.Transactions;
import application.repos.TransactionRepo;
import application.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;

    private final Mapper mapper;

    private final AccountClient accountClient;

    public TransferResponse initiateTransaction(String fromAccountId, String toAccountId, double amount, String description) throws AccountNotFoundException {

        AccountDetail fromAccount = new AccountDetail();
        AccountDetail toAccount = new AccountDetail();

        try{
            // Check if fromAccount account id exists
            fromAccount = accountClient.getAccountById(fromAccountId);

            // Check if toAccount account id exists
            toAccount = accountClient.getAccountById(toAccountId);
        }
        catch(Exception e){
            throw new AccountNotFoundException();
        }

        if (fromAccountId.equals(toAccountId)){
            throw new SameAccountTransferException();
        }

        double currentBalance = fromAccount.getBalance();

        if(amount > currentBalance){
             throw new InsufficientFundsException();
        }

        Transactions transactions = new Transactions();

        transactions.setFromAccountId(fromAccountId);
        transactions.setToAccountId(toAccountId);
        transactions.setAmount(amount);
        transactions.setDescription(description);
        transactions.setStatus(Status.INITIATED);
        transactions.setTimestamp(LocalDateTime.now());

        Transactions saved = transactionRepo.save(transactions);
//        System.out.println("Generated ID: " + saved.getTransactionId());

        TransferResponse response = new TransferResponse();
        response.setTransactionId(saved.getTransactionId());
        response.setStatus(Status.INITIATED);
        response.setTimestamp(LocalDateTime.now());

        return response;
    }

    public TransferResponse executeTransaction(String transactionId) {
        // Check ID
        Transactions transactions = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new FailedTransactionException());

        if (transactions.getStatus() != Status.INITIATED) {
            throw new FailedTransactionException();
        }

        try {
            // Debit
            AccountTransferRequest request = new AccountTransferRequest(
                    transactions.getFromAccountId(),
                    transactions.getToAccountId(),
                    transactions.getAmount()
            );
            accountClient.transfer(request);


            // Update transaction to SUCCESS
            transactions.setStatus(Status.SUCCESS);
            transactions.setTimestamp(LocalDateTime.now());
            transactionRepo.save(transactions);

            return new TransferResponse(
                    transactions.getTransactionId(),
                    Status.SUCCESS,
                    transactions.getTimestamp()
            );

        } catch (Exception ex) {
            // Mark transaction as FAILED
            transactions.setStatus(Status.FAILED);
            transactions.setTimestamp(LocalDateTime.now());
            transactionRepo.save(transactions);

            // Rethrow the exception
            throw ex;
        }
    }

    public TransactionDetailList getTransactionsList(String accountId) {
        // check on account id
        AccountDetail accountDetail = accountClient.getAccountById(accountId);

        List<Transactions> fromTransactions = transactionRepo.findByFromAccountId(accountId);
        List<Transactions> toTransactions = transactionRepo.findByToAccountId(accountId);

        if (fromTransactions.isEmpty() && toTransactions.isEmpty()) {
            throw new NoTransactionListException(accountId);
        }

        List<TransactionDetail> fromResponses = fromTransactions.stream()
                .filter(tx -> tx.getStatus() != Status.INITIATED)
                .map(tx -> {
                    TransactionDetail res = new TransactionDetail();
                    res.setTransactionId(tx.getTransactionId());
                    res.setFromAccountId(tx.getFromAccountId());
                    res.setToAccountId(tx.getToAccountId());
                    res.setAmount(-tx.getAmount());
                    res.setTimestamp(tx.getTimestamp());
                    res.setDescription(tx.getDescription() == null ? "" : tx.getDescription());
                    res.setDeliveryStatus(tx.getStatus() == Status.SUCCESS
                            ? DeliveryStatus.SENT : DeliveryStatus.FAILED);
                    return res;
                })
                .collect(Collectors.toList());



        List<TransactionDetail> toResponses = toTransactions.stream()
                .filter(tx -> tx.getStatus() != Status.INITIATED) // skip initiated
                .map(tx -> {
                    TransactionDetail res = new TransactionDetail();
                    res.setTransactionId(tx.getTransactionId());
                    res.setFromAccountId(tx.getFromAccountId());
                    res.setToAccountId(tx.getToAccountId());
                    res.setAmount(tx.getAmount());
                    res.setTimestamp(tx.getTimestamp());
                    res.setDescription(tx.getDescription() == null ? "" : tx.getDescription());
                    res.setDeliveryStatus(tx.getStatus() == Status.SUCCESS
                            ? DeliveryStatus.DELIVERED : DeliveryStatus.FAILED);
                    return res;
                })
                .collect(Collectors.toList());



        // Combine both lists
        List<TransactionDetail> allResponses = new ArrayList<>();
        allResponses.addAll(fromResponses);
        allResponses.addAll(toResponses);

        // Sort by timestamp descending
        allResponses.sort(Comparator.comparing(TransactionDetail::getTimestamp).reversed());

        // Build response
        TransactionDetailList response = new TransactionDetailList();
        response.setTransactionDetailList(allResponses);

        return response;
    }

}

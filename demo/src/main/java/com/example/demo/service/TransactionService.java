package com.example.demo.service;

import com.example.demo.dao.Transaction;
import com.example.demo.model.TransactionRequest;
import com.example.demo.model.TransactionResponse;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service("transactionService")
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private PriceService priceService;

    @Autowired
    private WalletService walletService;

    @Transactional(readOnly = true)
    public List<TransactionResponse> getTransaction(int userid){
        return transactionRepo.findByUserid(userid).stream().map(TransactionResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public TransactionResponse executeTransaction(TransactionRequest transactionExecutionRequestRequest){
        Transaction transaction = new Transaction(transactionExecutionRequestRequest);
        BigDecimal rate = priceService.getAggregatedPrice(transaction.getAction(),transaction.getCommodity());
        transaction.setRate(rate);
        transaction.calculateAmt();

        walletService.executeTransaction(transaction);

        return new TransactionResponse(transactionRepo.save(transaction));
    }


}

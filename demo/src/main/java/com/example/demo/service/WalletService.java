package com.example.demo.service;

import com.example.demo.constants.Constants;
import com.example.demo.dao.Transaction;
import com.example.demo.dao.Wallet;
import com.example.demo.exception.TranscationException;
import com.example.demo.model.WalletRequest;
import com.example.demo.model.WalletResponse;
import com.example.demo.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("walletService")
public class WalletService {

    @Autowired
    private WalletRepository walletRepo;

    @Transactional(readOnly = true)
    public List<WalletResponse> getWallet(int id){
        return walletRepo.findByUserId(id).stream().map(WalletResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public void executeTransaction(Transaction transaction) throws TranscationException {
        List<Wallet> wallet = walletRepo.findByUserId(transaction.getUserid());
        if(wallet.isEmpty()){
            throw new TranscationException("Wallet not found");
        }
        Wallet currencyWallet = new Wallet();
        Wallet transactionWallet = new Wallet();
        for(Wallet w : wallet){
            if(Constants.CURRENCY.equals(w.getCommodity())) {
                currencyWallet = w;
            }else if(transaction.getCommodity().equals(w.getCommodity())){
                transactionWallet = w;
            }
        }
        if(transaction.getAction().isBuy() && (currencyWallet.getAmount().compareTo(transaction.getAmt()) >= 0)){
            currencyWallet.setAmount(currencyWallet.getAmount().subtract(transaction.getAmt()));
            transactionWallet.setUserId(transaction.getUserid());
            transactionWallet.setCommodity(transaction.getCommodity());
            transactionWallet.setAmount(transactionWallet.getAmount().add(transaction.getCommodityQty()));
        }else if(transaction.getAction().isSell() && (transactionWallet.getAmount().compareTo(transaction.getCommodityQty()) >= 0)){
            transactionWallet.setAmount(transactionWallet.getAmount().subtract(transaction.getCommodityQty()));
            currencyWallet.setAmount(currencyWallet.getAmount().add(transaction.getAmt()));
        }else{
            throw new TranscationException("Insufficient Amount in Wallet");
        }

        List<Wallet> walletList = Arrays.asList(currencyWallet,transactionWallet);
        walletRepo.saveAll(walletList);

    }
}

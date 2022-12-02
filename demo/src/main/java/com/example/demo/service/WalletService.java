package com.example.demo.service;

import com.example.demo.dao.Wallet;
import com.example.demo.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("walletService")
public class WalletService {

    @Autowired
    private WalletRepository walletRepo;

}

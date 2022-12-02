package com.example.demo.controller;

import com.example.demo.dao.Wallet;
import com.example.demo.service.WalletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/wallet")
public class WalletController {

    @Resource
    private WalletService walletService;

    @GetMapping("/retrieve")
    public Wallet retrieveWallet(@RequestParam(value = "id") Long id){
        return new Wallet();
    }


}

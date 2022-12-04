package com.example.demo.controller;

import com.example.demo.model.WalletRequest;
import com.example.demo.model.WalletResponse;
import com.example.demo.service.WalletService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Resource
    private WalletService walletService;

    @GetMapping("/retrieve")
    public List<WalletResponse> retrieveWallet(@RequestParam(value = "id") @NotNull Integer id){
        return walletService.getWallet(id);
    }

    @PostMapping("/deposit")
    public WalletResponse deposit(@RequestBody WalletRequest wallet){
        return walletService.save(wallet);
    }



}

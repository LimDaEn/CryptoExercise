package com.example.demo.model;

import com.example.demo.dao.Wallet;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WalletResponse {

    private Integer userId;

    private String commodity;

    private BigDecimal amount;

    public WalletResponse(Wallet wallet){
        this.userId = wallet.getUserId();
        this.commodity = wallet.getCommodity();
        this.amount = wallet.getAmount();
    }
}

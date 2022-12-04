package com.example.demo.dao;

import com.example.demo.model.WalletRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "WALLET")
@IdClass(WalletId.class)
public class Wallet {

    @Id
    @Column(name="USER_ID")
    private Integer userId;

    @Id
    @Column(name="COMMODITY")
    private String commodity;

    @Column(name="AMOUNT")
    private BigDecimal amount;

    public Wallet() {
        amount = BigDecimal.ZERO;
    }

    public Wallet(WalletRequest wallet){
        this.userId = wallet.getUserId();
        this.commodity = wallet.getCommodity();
        this.amount = wallet.getAmount();
    }

}

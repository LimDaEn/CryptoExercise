package com.example.demo.dao;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private int userId;

    @Column(name="COMMODITY")
    private String commodity;

    @Column(name="AMOUNT")
    private BigDecimal amount;
}

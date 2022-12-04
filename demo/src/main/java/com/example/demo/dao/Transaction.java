package com.example.demo.dao;


import com.example.demo.model.TransactionRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Setter
@Getter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TRANSACTION_ID")
    private Integer transactionId;

    @Column(name="USER_ID")
    private Integer userid;

    @Column(name="ACTION")
    private Action action;

    @Column(name="COMMODITY")
    private String commodity;

    @Column(name="COMMODITY_QTY")
    private BigDecimal commodityQty;

    @Column(name="RATE")
    private BigDecimal rate;

    @Column(name="AMOUNT")
    private BigDecimal amt;

    public Transaction(TransactionRequest transactionExecutionRequest) {
        this.userid = transactionExecutionRequest.getUserid();
        this.action = transactionExecutionRequest.getAction();
        this.commodity = transactionExecutionRequest.getCommodity();
        this.commodityQty = transactionExecutionRequest.getCommodityQty();
    }

    public void calculateAmt(){
        amt = commodityQty.multiply(rate);
    }
}

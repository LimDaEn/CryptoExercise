package com.example.demo.model;

import com.example.demo.dao.Action;
import com.example.demo.dao.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionResponse {

    private Integer userid;
    private Action action;
    private String commodity;
    private BigDecimal commodityQty;

    private BigDecimal rate;

    public TransactionResponse(Transaction transaction){
        this.userid = transaction.getUserid();
        this.action = transaction.getAction();
        this.commodity = transaction.getCommodity();
        this.commodityQty = transaction.getCommodityQty();
        this.rate = transaction.getRate();
    }
}

package com.example.demo.model;

import com.example.demo.dao.Price;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceResponse {

    private String commodity;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private String source;

    public PriceResponse(Price price){
        this.commodity = price.getCommodity();
        this.buyPrice = price.getBuyPrice();
        this.sellPrice = price.getSellPrice();
        this.source = price.getSource();
    }

}

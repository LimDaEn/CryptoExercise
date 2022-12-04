package com.example.demo.service;

import com.example.demo.dao.Action;
import com.example.demo.dao.Price;
import com.example.demo.dao.PriceId;
import com.example.demo.model.PriceResponse;
import com.example.demo.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service("priceService")
public class PriceService {

    @Autowired
    private PriceRepository priceRepo;

    public Price getPrice(String source, String commodity){
        return priceRepo.findById(new PriceId(source,commodity)).get();
    }

    @Transactional(readOnly = true)
    public PriceResponse getAggregatedPrices(Action action, String commodity){

        List<Price> prices = priceRepo.findByCommodity(commodity);
        Price aggregatePrice = prices.get(0);
        for(Price p : prices){
            if(action.isBuy()){
                //Buy - Get the smallest sell price
                if(p.getBuyPrice().compareTo(aggregatePrice.getBuyPrice()) < 0){
                    aggregatePrice = p;
                }
            }else{
                //Sell - Get the largest sell price
                if(p.getSellPrice().compareTo(aggregatePrice.getSellPrice()) > 0){
                    aggregatePrice = p;
                }
            }
        }

        return new PriceResponse(aggregatePrice);
    }

    public BigDecimal getAggregatedPrice(Action action, String commodity){
        List<Price> prices = priceRepo.findByCommodity(commodity);

        BigDecimal requestedPrice;
        if(action.isBuy()){
            requestedPrice = prices.get(0).getBuyPrice();
        }else{
            requestedPrice = prices.get(0).getSellPrice();
        }
        for(Price p : prices){
            if(action.isSell()){
                //Buy - Get the smallest sell price
                if(p.getBuyPrice().compareTo(requestedPrice) < 0){
                    requestedPrice = p.getBuyPrice();
                }
            }else{
                //Sell - Get the largest sell price
                if(p.getSellPrice().compareTo(requestedPrice) > 0){
                    requestedPrice = p.getSellPrice();
                }
            }
        }
        return requestedPrice;
    }


    public void savePrice(List<Price> price){
        priceRepo.saveAll(price);
    }

}

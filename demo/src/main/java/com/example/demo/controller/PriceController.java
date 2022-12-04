package com.example.demo.controller;

import com.example.demo.dao.Action;
import com.example.demo.model.PriceResponse;
import com.example.demo.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Resource
    private PriceService priceService;

    @GetMapping("/getAggregatePrices")
    public PriceResponse getAggregatePrices(@RequestParam @NotNull Action action,
                                            @RequestParam @NotNull String commodity){
        return priceService.getAggregatedPrices(action,commodity);
    }


}

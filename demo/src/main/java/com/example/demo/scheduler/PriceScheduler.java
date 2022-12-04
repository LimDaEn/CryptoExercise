package com.example.demo.scheduler;

import com.example.demo.constants.Constants;
import com.example.demo.dao.Price;
import com.example.demo.service.PriceService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Configuration
@EnableScheduling
public class PriceScheduler {

    public static final List<String> tradingList = Arrays.asList("ETHUSDT","BTCUSDT","ethusdt","btcusdt");

    @Resource
    private PriceService priceService;

    private RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        if(null == restTemplate){
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

    @Scheduled(fixedDelay = 10000)
    public void schedulePriceRetrieval() {
        retrievePrice(Constants.BINANCE_URL);
        retrievePrice(Constants.HUOBI_URL);
    }

    public void retrievePrice(String url){
        try{
            ResponseEntity<String> response = getRestTemplate().getForEntity(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            List<Price> priceList = new ArrayList<>();

            switch(url){
                case Constants.BINANCE_URL:
                    for(JsonNode data : root){
                        if(tradingList.contains(data.get("symbol").asText())){
                            Price price = new Price(
                                    data.get("symbol").asText().toUpperCase(),
                                    new BigDecimal(data.get("askPrice").asText()),
                                    new BigDecimal(data.get("bidPrice").asText()),
                                    "BINANCE");
                            priceList.add(price);
                        }
                    }
                    break;
                case Constants.HUOBI_URL:
                    root = root.get("data");
                    for(JsonNode data : root){
                        if(tradingList.contains(data.get("symbol").asText())){
                            Price price = new Price(
                                    data.get("symbol").asText().toUpperCase(),
                                    new BigDecimal(data.get("ask").asText()),
                                    new BigDecimal(data.get("bid").asText()),
                                    "HUOBI");
                            priceList.add(price);
                        }
                    }
            }
            priceService.savePrice(priceList);

        }catch (Exception e){
            log.error(e);
        }
    }
}

package com.example.demo.model;

import com.example.demo.dao.Action;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {

    @NotNull(message = "Please enter a userid")
    private Integer userid;

    @NotNull(message = "Please enter a action")
    private Action action;

    @NotNull(message = "Please enter a commodity")
    private String commodity;

    @NotNull(message = "Please enter a commodityQty")
    @Min(value = 0, message="commodityQty cannot be below 0")
    private BigDecimal commodityQty;


}

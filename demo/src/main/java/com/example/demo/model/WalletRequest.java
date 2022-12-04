package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class WalletRequest {

    @NotNull(message = "Please enter a userid")
    private Integer userId;

    @NotNull(message = "Please enter a commodity")
    private String commodity;

    @NotNull(message = "Please enter a amount")
    private BigDecimal amount;
}

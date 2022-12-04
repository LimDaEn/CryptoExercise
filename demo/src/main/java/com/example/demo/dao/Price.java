package com.example.demo.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="PRICES")
@IdClass(PriceId.class)
public class Price {
    @Id
    @Column(name="COMMODITY")
    private String commodity;
    @Column(name="BUYPRICE")
    private BigDecimal buyPrice;
    @Column(name="SELLPRICE")
    private BigDecimal sellPrice;
    @Id
    @Column(name="SOURCE")
    private String source;

}

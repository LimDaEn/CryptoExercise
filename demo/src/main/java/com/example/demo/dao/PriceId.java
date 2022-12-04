package com.example.demo.dao;

import java.io.Serializable;
import java.util.Objects;

public class PriceId implements Serializable {

    private String source;
    private String commodity;

    public PriceId() {
    }

    public PriceId(String source, String commodity) {
        this.source = source;
        this.commodity = commodity;
    }
}

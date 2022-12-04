package com.example.demo.dao;

import java.io.Serializable;

public class WalletId implements Serializable {

    private Integer userId;
    private String commodity;

    public WalletId() {
    }

    public WalletId(int userId, String commodity) {
        this.userId = userId;
        this.commodity = commodity;
    }
}

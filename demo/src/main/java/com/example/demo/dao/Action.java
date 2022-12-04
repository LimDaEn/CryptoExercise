package com.example.demo.dao;

public enum Action {
    Buy,Sell;

    public boolean isBuy(){
        return this.equals(Buy);
    }

    public boolean isSell(){
        return this.equals(Sell);
    }
}

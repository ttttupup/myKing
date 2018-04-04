package com.example.hugy.kingeconomy.bean;

/**
 * Created by hugy on 2018/3/26.
 */

public class Task {
    private String  name;
    private String  amount;
    public Task(){

    }
    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

package com.eg.ihse.service.request;

public class AddStock2ExchangeServiceReq {

    public final String exchangeName;
    public final String stockName;

    public AddStock2ExchangeServiceReq(String exchangeName, String stockName) {
        this.exchangeName = exchangeName;
        this.stockName = stockName;
    }
}

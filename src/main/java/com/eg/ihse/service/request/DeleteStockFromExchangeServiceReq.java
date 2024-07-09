package com.eg.ihse.service.request;

public class DeleteStockFromExchangeServiceReq {

    public final String exchangeName;
    public final String stockName;

    public DeleteStockFromExchangeServiceReq(String exchangeName, String stockName) {
        this.exchangeName = exchangeName;
        this.stockName = stockName;
    }
}
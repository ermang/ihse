package com.eg.ihse.util;

import com.eg.ihse.controller.request.CreateStockExchangeReq;
import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.entity.StockExchange;
import org.springframework.stereotype.Service;

@Service
public class Req2Entity {

    public Stock createStockReq2Stock(CreateStockReq csr) {
        Stock s = new Stock();

        s.setName(csr.name);
        s.setDescription(csr.description);
        s.setPrice(csr.price);

        return s;
    }

    public StockExchange createStockExchangeReq2StockExchange(CreateStockExchangeReq cser) {

        StockExchange se = new StockExchange();
        se.setName(cser.name);
        se.setDescription(cser.description);
        se.setLive(false);

        return se;
    }
}

package com.eg.ihse.util;

import com.eg.ihse.controller.request.CreateExchangeReq;
import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.entity.Exchange;
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

    public Exchange createStockExchangeReq2StockExchange(CreateExchangeReq cser) {

        Exchange se = new Exchange();
        se.setName(cser.name);
        se.setDescription(cser.description);
        se.setLive(false);

        return se;
    }
}

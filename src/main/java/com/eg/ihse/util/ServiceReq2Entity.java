package com.eg.ihse.util;

import com.eg.ihse.entity.Exchange;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.service.request.CreateExchangeServiceReq;
import com.eg.ihse.service.request.CreateStockServiceReq;
import org.springframework.stereotype.Component;

@Component
public class ServiceReq2Entity {

    public Stock createStockServiceReq2Stock(CreateStockServiceReq serviceReq) {
        Stock s = new Stock();

        s.setName(serviceReq.name);
        s.setDescription(serviceReq.description);
        s.setPrice(serviceReq.price);

        return s;
    }

    public Exchange createExchangeReq2Exchange(CreateExchangeServiceReq serviceReq) {

        Exchange e = new Exchange();
        e.setName(serviceReq.name);
        e.setDescription(serviceReq.description);
        e.setLive(false);

        return e;
    }
}

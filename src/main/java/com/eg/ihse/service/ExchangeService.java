package com.eg.ihse.service;

import com.eg.ihse.controller.request.AddStock2ExchangeReq;
import com.eg.ihse.controller.request.CreateStockExchangeReq;
import com.eg.ihse.entity.Exchange;
import com.eg.ihse.entity.StockExchangeRel;
import com.eg.ihse.repo.ExchangeRepo;
import com.eg.ihse.repo.StockExchangeRelRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.util.Req2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ExchangeService {



    private final ExchangeRepo exchangeRepo;
    private final StockRepo stockRepo;
    private final StockExchangeRelRepo stockExchangeRelRepo;
    private final Req2Entity req2Entity;

    public ExchangeService(ExchangeRepo exchangeRepo, StockRepo stockRepo, StockExchangeRelRepo stockExchangeRelRepo, Req2Entity req2Entity) {
        this.exchangeRepo = exchangeRepo;
        this.stockRepo = stockRepo;
        this.stockExchangeRelRepo = stockExchangeRelRepo;
        this.req2Entity = req2Entity;
    }

    public void createStockExchange(CreateStockExchangeReq createStockExchangeReq) {
        Exchange exchange = req2Entity.createStockExchangeReq2StockExchange(createStockExchangeReq);

        exchangeRepo.save(exchange);
    }

    public void addStock2Exchange(AddStock2ExchangeReq addStock2ExchangeReq) {


        //TODO: decide on this
        //List<StockExchangeRel> relList = stockExchangeRelRepo.findAllByExchangeName(addStock2ExchangeReq.exchangeName);
//
//        if(exchange == null)
//            throw new RuntimeException();

        //TODO: deal with null checks
        StockExchangeRel stockExchangeRel = new StockExchangeRel();
        stockExchangeRel.setExchange(exchangeRepo.findByName(addStock2ExchangeReq.exchangeName));
        stockExchangeRel.setStock(stockRepo.findByName(addStock2ExchangeReq.stockName));

        stockExchangeRelRepo.save(stockExchangeRel);

        int x= 5;
    }
}

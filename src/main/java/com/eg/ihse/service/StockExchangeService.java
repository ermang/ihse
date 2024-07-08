package com.eg.ihse.service;

import com.eg.ihse.controller.request.CreateStockExchangeReq;
import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.entity.StockExchange;
import com.eg.ihse.repo.StockExchangeRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.util.Req2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StockExchangeService {



    private final StockExchangeRepo stockExchangeRepo;
    private final Req2Entity req2Entity;

    public StockExchangeService(StockExchangeRepo stockExchangeRepo, Req2Entity req2Entity) {
        this.stockExchangeRepo = stockExchangeRepo;
        this.req2Entity = req2Entity;
    }
    @Transactional
    public void createStockExchange(CreateStockExchangeReq createStockExchangeReq) {
        StockExchange stockExchange = req2Entity.createStockExchangeReq2StockExchange(createStockExchangeReq);

        stockExchangeRepo.save(stockExchange);
    }
}

package com.eg.ihse.service;

import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.controller.request.UpdateStockPriceReq;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.repo.StockExchangeRelRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.util.Req2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Transactional
@Service
public class StockService {

    private final StockRepo stockRepo;
    private final StockExchangeRelRepo stockExchangeRelRepo;
    private final Req2Entity req2Entity;

    public StockService(StockRepo stockRepo, StockExchangeRelRepo stockExchangeRelRepo, Req2Entity req2Entity) {
        this.stockRepo = stockRepo;
        this.stockExchangeRelRepo = stockExchangeRelRepo;
        this.req2Entity = req2Entity;
    }

    public void createStock(CreateStockReq createStockReq) {

        if (stockRepo.findByName(createStockReq.name) != null)
            throw new IllegalArgumentException(MessageFormat.format("stock with name {0} already exists", createStockReq.name));

        Stock stock = req2Entity.createStockReq2Stock(createStockReq);

        stockRepo.save(stock);
    }

    //TODO: @Version works and solves org.springframework.orm.ObjectOptimisticLockingFailureException
    public void updatePrice(UpdateStockPriceReq updateStockPriceReq) {

        Stock stock = stockRepo.findByName(updateStockPriceReq.name);

        if(stock == null)
            throw new IllegalArgumentException(MessageFormat.format("stock with name {0} does not exist", updateStockPriceReq.name));

        stock.setPrice(updateStockPriceReq.price);

        stockRepo.save(stock);
    }

    //TODO: deal with excepiton messages
    public void deleteStock(String stockName) {

        if (stockExchangeRelRepo.existsByStockName(stockName))
            throw new IllegalArgumentException(MessageFormat.format("stock with name {0} is registered to exchange, can not be deleted", stockName));
        else
            stockRepo.deleteByName(stockName);
    }
}

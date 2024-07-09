package com.eg.ihse.service;

import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.controller.request.UpdateStockPriceReq;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.repo.StockExchangeRelRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.service.request.CreateStockServiceReq;
import com.eg.ihse.service.request.UpdateStockPriceServiceReq;
import com.eg.ihse.util.Req2Entity;
import com.eg.ihse.util.ServiceReq2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Transactional
@Service
public class StockService {

    private final ServiceReq2Entity serviceReq2Entity;
    private final StockRepo stockRepo;
    private final StockExchangeRelRepo stockExchangeRelRepo;

    public StockService(ServiceReq2Entity serviceReq2Entity, StockRepo stockRepo, StockExchangeRelRepo stockExchangeRelRepo) {
        this.serviceReq2Entity = serviceReq2Entity;
        this.stockRepo = stockRepo;
        this.stockExchangeRelRepo = stockExchangeRelRepo;
    }

    public void createStock(CreateStockServiceReq createStockServiceReq) {

        if (stockRepo.findByName(createStockServiceReq.name) != null)
            throw new IllegalArgumentException(MessageFormat.format("stock with name {0} already exists", createStockServiceReq.name));

        Stock stock = serviceReq2Entity.createStockServiceReq2Stock(createStockServiceReq);

        stockRepo.save(stock);
    }

    //TODO: @Version works and solves org.springframework.orm.ObjectOptimisticLockingFailureException
    public void updatePrice(UpdateStockPriceServiceReq updateStockPriceServiceReq) {

        Stock stock = stockRepo.findByName(updateStockPriceServiceReq.name);

        if(stock == null)
            throw new IllegalArgumentException(MessageFormat.format("stock with name {0} does not exist", updateStockPriceServiceReq.name));

        stock.setPrice(updateStockPriceServiceReq.price);

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

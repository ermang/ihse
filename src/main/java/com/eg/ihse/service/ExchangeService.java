package com.eg.ihse.service;

import com.eg.ihse.controller.request.AddStock2ExchangeReq;
import com.eg.ihse.controller.request.CreateExchangeReq;
import com.eg.ihse.controller.request.DeleteStockFromExchangeReq;
import com.eg.ihse.entity.Exchange;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.entity.StockExchangeRel;
import com.eg.ihse.entity.projection.ReadStock;
import com.eg.ihse.repo.ExchangeRepo;
import com.eg.ihse.repo.StockExchangeRelRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.util.Constant;
import com.eg.ihse.util.Req2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
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

    public void createExchange(CreateExchangeReq createExchangeReq) {

        if (exchangeRepo.findByName(createExchangeReq.name) != null)
            throw new IllegalArgumentException(MessageFormat.format("exchange with name {0} already exists", createExchangeReq.name));

        Exchange exchange = req2Entity.createExchangeReq2Exchange(createExchangeReq);

        exchangeRepo.save(exchange);
    }

    //TODO: may need extra locking mechanism for count() decide
    public void addStock2Exchange(AddStock2ExchangeReq addStock2ExchangeReq) {

        Exchange exchange = exchangeRepo.findByName(addStock2ExchangeReq.exchangeName);

        if (exchange == null)
            throw new IllegalArgumentException(MessageFormat.format("exchange with name {0} does not exist", addStock2ExchangeReq.exchangeName));

        Stock stock = stockRepo.findByName(addStock2ExchangeReq.stockName);

        if (stock == null)
            throw new IllegalArgumentException(MessageFormat.format("stock with name {0} does not exist", addStock2ExchangeReq.stockName));

        if (stockExchangeRelRepo.findByExchangeNameAndStockName(addStock2ExchangeReq.exchangeName, addStock2ExchangeReq.stockName) != null)
            throw new IllegalArgumentException(MessageFormat.format("stock {0} is already registered to exchange {1}", addStock2ExchangeReq.stockName, addStock2ExchangeReq.exchangeName));

        StockExchangeRel stockExchangeRel = new StockExchangeRel();
        stockExchangeRel.setExchange(exchange);
        stockExchangeRel.setStock(stock);

        stockExchangeRelRepo.save(stockExchangeRel);

        long numberOfStocks = stockExchangeRelRepo.countByExchangeName(addStock2ExchangeReq.exchangeName);

        if (numberOfStocks >= Constant.MIN_STOCK_COUNT_FOR_LIVE)
            exchange.setLive(true);
        else
            exchange.setLive(false);

        exchangeRepo.save(exchange);
    }

    public void deleteStockFromExchange(DeleteStockFromExchangeReq deleteStockFromExchangeReq) {

        if (stockExchangeRelRepo.findByExchangeNameAndStockName(deleteStockFromExchangeReq.exchangeName, deleteStockFromExchangeReq.stockName) == null)
            throw new IllegalArgumentException(MessageFormat.format("stock {0} is not registered to exchange {1}", deleteStockFromExchangeReq.stockName, deleteStockFromExchangeReq.exchangeName));

        Exchange exchange = exchangeRepo.findByName(deleteStockFromExchangeReq.exchangeName);

        stockExchangeRelRepo.deleteByExchangeNameAndStockName(deleteStockFromExchangeReq.exchangeName, deleteStockFromExchangeReq.stockName);

        long numberOfStocks = stockExchangeRelRepo.countByExchangeName(deleteStockFromExchangeReq.exchangeName);

        if (numberOfStocks >= Constant.MIN_STOCK_COUNT_FOR_LIVE)
            exchange.setLive(true);
        else
            exchange.setLive(false);

        exchangeRepo.save(exchange);
    }

    public List<ReadStock> getAllStocksInExchange(String exchangeName) {

        List<ReadStock> stockList = stockRepo.findAllStocksInExchangeRO(exchangeName);

        return stockList;
    }
}

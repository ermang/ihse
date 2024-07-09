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
import com.eg.ihse.service.request.AddStock2ExchangeServiceReq;
import com.eg.ihse.service.request.CreateExchangeServiceReq;
import com.eg.ihse.service.request.DeleteStockFromExchangeServiceReq;
import com.eg.ihse.util.Constant;
import com.eg.ihse.util.Req2Entity;
import com.eg.ihse.util.ServiceReq2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Transactional
@Service
public class ExchangeService {

    private final ServiceReq2Entity serviceReq2Entity;
    private final ExchangeRepo exchangeRepo;
    private final StockRepo stockRepo;
    private final StockExchangeRelRepo stockExchangeRelRepo;

    public ExchangeService(ServiceReq2Entity serviceReq2Entity, ExchangeRepo exchangeRepo, StockRepo stockRepo, StockExchangeRelRepo stockExchangeRelRepo) {
        this.serviceReq2Entity = serviceReq2Entity;
        this.exchangeRepo = exchangeRepo;
        this.stockRepo = stockRepo;
        this.stockExchangeRelRepo = stockExchangeRelRepo;
    }

    public void createExchange(CreateExchangeServiceReq createExchangeServiceReq) {

        if (exchangeRepo.findByName(createExchangeServiceReq.name) != null)
            throw new IllegalArgumentException(MessageFormat.format("exchange with name {0} already exists", createExchangeServiceReq.name));

        Exchange exchange = serviceReq2Entity.createExchangeReq2Exchange(createExchangeServiceReq);

        exchangeRepo.save(exchange);
    }

    //TODO: may need extra locking mechanism for count() decide
    public void addStock2Exchange(AddStock2ExchangeServiceReq addStock2ExchangeServiceReq) {

        Exchange exchange = exchangeRepo.findByName(addStock2ExchangeServiceReq.exchangeName);

        if (exchange == null)
            throw new IllegalArgumentException(MessageFormat.format("exchange with name {0} does not exist", addStock2ExchangeServiceReq.exchangeName));

        Stock stock = stockRepo.findByName(addStock2ExchangeServiceReq.stockName);

        if (stock == null)
            throw new IllegalArgumentException(MessageFormat.format("stock with name {0} does not exist", addStock2ExchangeServiceReq.stockName));

        if (stockExchangeRelRepo.findByExchangeNameAndStockName(addStock2ExchangeServiceReq.exchangeName, addStock2ExchangeServiceReq.stockName) != null)
            throw new IllegalArgumentException(MessageFormat.format("stock {0} is already registered to exchange {1}", addStock2ExchangeServiceReq.stockName, addStock2ExchangeServiceReq.exchangeName));

        StockExchangeRel stockExchangeRel = new StockExchangeRel();
        stockExchangeRel.setExchange(exchange);
        stockExchangeRel.setStock(stock);

        stockExchangeRelRepo.save(stockExchangeRel);

        long numberOfStocks = stockExchangeRelRepo.countByExchangeName(addStock2ExchangeServiceReq.exchangeName);

        if (numberOfStocks >= Constant.MIN_STOCK_COUNT_FOR_LIVE)
            exchange.setLive(true);
        else
            exchange.setLive(false);

        exchangeRepo.save(exchange);
    }

    public void deleteStockFromExchange(DeleteStockFromExchangeServiceReq deleteStockFromExchangeServiceReq) {

        if (stockExchangeRelRepo.findByExchangeNameAndStockName(deleteStockFromExchangeServiceReq.exchangeName, deleteStockFromExchangeServiceReq.stockName) == null)
            throw new IllegalArgumentException(MessageFormat.format("stock {0} is not registered to exchange {1}", deleteStockFromExchangeServiceReq.stockName, deleteStockFromExchangeServiceReq.exchangeName));

        Exchange exchange = exchangeRepo.findByName(deleteStockFromExchangeServiceReq.exchangeName);

        stockExchangeRelRepo.deleteByExchangeNameAndStockName(deleteStockFromExchangeServiceReq.exchangeName, deleteStockFromExchangeServiceReq.stockName);

        long numberOfStocks = stockExchangeRelRepo.countByExchangeName(deleteStockFromExchangeServiceReq.exchangeName);

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

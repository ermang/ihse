package com.eg.ihse.controller;

import com.eg.ihse.controller.helper.RequestValidator;
import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.controller.request.UpdateStockPriceReq;
import com.eg.ihse.service.StockService;
import com.eg.ihse.service.request.CreateStockServiceReq;
import com.eg.ihse.service.request.UpdateStockPriceServiceReq;
import com.eg.ihse.util.Req2ServiceReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final RequestValidator requestValidator;
    private final Req2ServiceReq req2ServiceReq;
    private final StockService stockService;

    public StockController(RequestValidator requestValidator, Req2ServiceReq req2ServiceReq, StockService stockService) {
        this.requestValidator = requestValidator;
        this.req2ServiceReq = req2ServiceReq;
        this.stockService = stockService;
    }

    @PostMapping
    public void createStock(@RequestBody @Valid CreateStockReq createStockReq){
        requestValidator.validate(createStockReq);
        CreateStockServiceReq serviceReq = req2ServiceReq.createStockReq2CreateStockServiceReq(createStockReq);
        stockService.createStock(serviceReq);
    }

    @PutMapping
    public void updateStockPrice(@RequestBody @Valid UpdateStockPriceReq updateStockPriceReq) {
        UpdateStockPriceServiceReq serviceReq = req2ServiceReq.updateStockPriceReq2UpdateStockPriceServiceReq(updateStockPriceReq);
        stockService.updatePrice(serviceReq);
    }

    @DeleteMapping("/{stockName}")
    public void deleteStock(@PathVariable String stockName){
        stockService.deleteStock(stockName);
    }
}

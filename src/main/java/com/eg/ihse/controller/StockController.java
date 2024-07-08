package com.eg.ihse.controller;

import com.eg.ihse.controller.helper.RequestValidator;
import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.controller.request.UpdateStockPriceReq;
import com.eg.ihse.service.StockService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final RequestValidator requestValidator;
    private final StockService stockService;

    public StockController(RequestValidator requestValidator, StockService stockService) {
        this.requestValidator = requestValidator;
        this.stockService = stockService;
    }

    @PostMapping
    public void createStock(@RequestBody @Valid CreateStockReq createStockReq){
        requestValidator.validate(createStockReq);
        stockService.createStock(createStockReq);
    }

    @PutMapping
    public void updateStockPrice(@RequestBody @Valid UpdateStockPriceReq updateStockPriceReq) {
        stockService.updatePrice(updateStockPriceReq);
    }

    @DeleteMapping("/{stockName}")
    public void deleteStock(@PathVariable String stockName){
        stockService.deleteStock(stockName);
    }
}

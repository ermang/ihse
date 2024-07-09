package com.eg.ihse.controller;

import com.eg.ihse.controller.helper.RequestValidator;
import com.eg.ihse.controller.request.AddStock2ExchangeReq;
import com.eg.ihse.controller.request.CreateExchangeReq;
import com.eg.ihse.controller.request.DeleteStockFromExchangeReq;
import com.eg.ihse.entity.projection.ReadStock;
import com.eg.ihse.service.ExchangeService;
import com.eg.ihse.service.request.AddStock2ExchangeServiceReq;
import com.eg.ihse.service.request.CreateExchangeServiceReq;
import com.eg.ihse.service.request.DeleteStockFromExchangeServiceReq;
import com.eg.ihse.util.Req2ServiceReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-exchange")
public class ExchangeController {

    private final RequestValidator requestValidator;
    private final Req2ServiceReq req2ServiceReq;
    private final ExchangeService exchangeService;

    public ExchangeController(RequestValidator requestValidator, Req2ServiceReq req2ServiceReq, ExchangeService exchangeService) {
        this.requestValidator = requestValidator;
        this.req2ServiceReq = req2ServiceReq;
        this.exchangeService = exchangeService;
    }

    @PostMapping
    public void createExchange(@RequestBody @Valid CreateExchangeReq createExchangeReq) {
        requestValidator.validate(createExchangeReq);
        CreateExchangeServiceReq serviceReq = req2ServiceReq.createExchangeReq2CreateExchangeServiceReq(createExchangeReq);
        exchangeService.createExchange(serviceReq);
    }

    @GetMapping("/{exchangeName}")
    public List<ReadStock> getAllStocksInExchange(@PathVariable String exchangeName) {
        List<ReadStock> result = exchangeService.getAllStocksInExchange(exchangeName);

        return result;
    }

    @PostMapping("/stock")
    public void addStock(@RequestBody @Valid AddStock2ExchangeReq addStock2ExchangeReq) {
        AddStock2ExchangeServiceReq serviceReq = req2ServiceReq.addStock2ExchangeReq2AddStock2ExchangeServiceReq(addStock2ExchangeReq);
        exchangeService.addStock2Exchange(serviceReq);
    }

    @DeleteMapping("/stock")
    public void deleteStock(@RequestBody @Valid DeleteStockFromExchangeReq deleteStockFromExchangeReq) {
        DeleteStockFromExchangeServiceReq serviceReq = req2ServiceReq.deleteStockFromExchangeReq2DeleteStockFromExchangeServiceReq(deleteStockFromExchangeReq);
        exchangeService.deleteStockFromExchange(serviceReq);
    }
}

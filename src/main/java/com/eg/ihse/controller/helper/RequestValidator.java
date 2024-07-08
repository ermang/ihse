package com.eg.ihse.controller.helper;

import com.eg.ihse.controller.request.CreateExchangeReq;
import com.eg.ihse.controller.request.CreateStockReq;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public void validate(CreateStockReq req) {
        if (!req.name.equals(req.name.toUpperCase()))
            throw new IllegalArgumentException("name must be upper-case");
    }

    public void validate(CreateExchangeReq req) {
        if (!req.name.equals(req.name.toUpperCase()))
            throw new IllegalArgumentException("name must be upper-case");
    }

}

package com.eg.ihse.controller.request;

import javax.validation.constraints.NotBlank;

public class DeleteStockFromExchangeReq {
    @NotBlank(message = "exchangeName can not be blank")
    public String exchangeName;

    @NotBlank(message = "stockName can not be blank")
    public String stockName;
}

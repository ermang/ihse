package com.eg.ihse.controller.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CreateStockExchangeReq {
    @NotBlank(message = "name can not be blank")
    public String name;
    @NotBlank(message = "description can not be blank")
    public String description;
}

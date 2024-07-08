package com.eg.ihse.controller.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class UpdateStockPriceReq {
    @NotBlank(message = "name can not be blank")
    public String name;

    @Positive(message = "price must be positive")
    @NotNull(message = "price can not be null")
    @Digits(integer = 5, fraction = 4, message = "max integer digits 5 max fraction digits 4")
    public BigDecimal price;
}

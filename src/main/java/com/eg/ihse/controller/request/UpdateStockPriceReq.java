package com.eg.ihse.controller.request;

import com.eg.ihse.util.Constant;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class UpdateStockPriceReq {
    @Size(min = Constant.MIN_NAME_SIZE, max = Constant.MAX_NAME_SIZE, message = "name size must be between " + Constant.MIN_NAME_SIZE + "-" + Constant.MAX_NAME_SIZE)
    @NotBlank(message = "name can not be blank")
    public String name;

    @Positive(message = "price must be positive")
    @NotNull(message = "price can not be null")
    @Digits(integer = Constant.STOCK_PRICE_MIN_INTEGER_PART, fraction = Constant.STOCK_PRICE_MIN_FRACTIONAL_PART,
            message =  "stock price max integer digits " + Constant.STOCK_PRICE_MIN_INTEGER_PART + " max fraction digits " + Constant.STOCK_PRICE_MIN_FRACTIONAL_PART)
    public BigDecimal price;
}

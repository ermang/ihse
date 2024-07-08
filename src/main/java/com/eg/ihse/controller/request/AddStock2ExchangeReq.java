package com.eg.ihse.controller.request;

import com.eg.ihse.util.Constant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddStock2ExchangeReq {
    @Size(min = Constant.MIN_NAME_SIZE, max = Constant.MAX_NAME_SIZE, message = "exchangeName size must be between" + Constant.MIN_NAME_SIZE + "-" + Constant.MAX_NAME_SIZE)
    @NotBlank(message = "exchangeName can not be blank")
    public String exchangeName;

    @Size(min = Constant.MIN_NAME_SIZE, max = Constant.MAX_NAME_SIZE, message = "stockName size must be between" + Constant.MIN_NAME_SIZE + "-" + Constant.MAX_NAME_SIZE)
    @NotBlank(message = "stockName can not be blank")
    public String stockName;
}

package com.eg.ihse.controller.request;

import com.eg.ihse.util.Constant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateExchangeReq {
    @Size(min = Constant.MIN_NAME_SIZE, max = Constant.MAX_NAME_SIZE, message = "name size must be between " + Constant.MIN_NAME_SIZE + "-" + Constant.MAX_NAME_SIZE)
    @NotBlank(message = "name can not be blank")
    public String name;
    @NotBlank(message = "description can not be blank")
    public String description;
}

package com.eg.ihse.controller.request;

import javax.validation.constraints.NotBlank;

public class CreateStockReq {
    @NotBlank(message = "name can not be blank")
    public String name;
    @NotBlank(message = "description can not be blank")
    public String description;
}

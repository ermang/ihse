package com.eg.ihse.service.request;

public class CreateExchangeServiceReq {

    public final String name;
    public final String description;

    public CreateExchangeServiceReq(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

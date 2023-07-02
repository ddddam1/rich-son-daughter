package com.starter.stock.dto;


import lombok.Data;

@Data
public class RequestDto {

    private int price;

    private String stockName;

    private String buyDate;

    private String sellDate;
}

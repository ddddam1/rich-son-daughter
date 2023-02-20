package com.starer.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {

    private int price;

    private String stockName;

    private String baseDate;
}

package com.starer.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(
    collection = "search"
)
public class RequestDto {

    private int price;

    private String stockName;

    private String baseDate;
}

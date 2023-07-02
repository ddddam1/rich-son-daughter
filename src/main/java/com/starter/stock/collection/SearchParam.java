package com.starter.stock.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(
        collection = "search"
)
public class SearchParam {

    private int price;

    private String stockName;

    private String baseDate;
}
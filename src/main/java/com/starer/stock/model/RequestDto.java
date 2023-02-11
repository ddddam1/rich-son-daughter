package com.starer.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {

    @NumberFormat(pattern = "###,###")
//    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Integer price;

//    @NumberFormat(pattern = "###,###")
//    public Integer getPrice() {
//        return price;
//    }
//    @NumberFormat(pattern = "###,###")
//    public void setPrice(Integer price) {
//        this.price = price;
//    }
}

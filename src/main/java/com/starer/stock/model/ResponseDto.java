package com.starer.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto {

    @JsonProperty("itmsNm")
    private String stockName;

    @JsonProperty("basDt")
    private String baseDate;

    @JsonProperty("mrktCtg")
    private String marketClass;

    @JsonProperty("isinCd")
    private String isinCode;

    @JsonProperty("clpr")
    private int closingPrice;

    @JsonProperty("vs")
    private int versus;

    @JsonProperty("fltRt")
    private float versusRate;

    @NumberFormat(pattern = "###,###")
    @JsonProperty("mkp")
    private int marketPrice;

    @JsonProperty("hipr")
    private int highPrice;

    @JsonProperty("lopr")
    private int lowPrice;

    @JsonProperty("trqu")
    private long tradeQuantity;

    @JsonProperty("trPrc")
    private long tradeTotalPrice;

    @JsonProperty("lstgStCnt")
    private long listedStockCount;

    @JsonProperty("mrktTotAmt")
    private long marketTotalAmount;






}

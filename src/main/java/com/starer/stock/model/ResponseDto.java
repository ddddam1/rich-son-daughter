package com.starer.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.NumberFormat;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(
    collection = "stock"
)
public class ResponseDto {

    @JsonIgnore
    private final transient int CAPITAL = 50000000;

    @MongoId(FieldType.OBJECT_ID)
    private String id;

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

    private int stockCount;





}

package com.starer.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SampleDto {

    @JsonProperty("itmsNm")
    private String stockName;

    @JsonProperty("basDt")
    private String baseDate;

    @JsonProperty("mrktCtg")
    private String marketClass;

    private String isinCd;

    private int clpr;

    private int vs;

    private float fltRt;
    private int mkp;
    private int hipr;
    private int lopr;
    private long trqu;
    private long trPrc;
    private long lstgStCnt;
    private long mrktTotAmt;






}

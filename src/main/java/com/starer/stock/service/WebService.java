package com.starer.stock.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.starer.stock.model.SampleDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class WebService {

    private final WebClient webClient;

    public String useWebClient() throws UnsupportedEncodingException {

        String serviceKey = "L287ZhVphoAlHm6Qs1C7f3H%2FpwT6%2BYlr4TK7t7AxhoMKsUtThnlwPHpnnYfbpwLomNF6wxNm%2FQh0N8EoRH4Rpw%3D%3D";

        String result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", serviceKey)
                        .queryParam("numOfRows", 10)
                        .queryParam("pageNo", 1)
                        .queryParam("resultType", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject jsonObject = new JSONObject(result);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");

        return body.toString();

    }

}

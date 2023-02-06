package com.starer.stock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starer.stock.AppConfig;
import com.starer.stock.model.SampleDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class WebService {

    private final WebClient webClient;
    private final DocumentBuilder dBuilder;
    private final ObjectMapper objectMapper;

    // tag값의 정보를 가져오는 함수

    public static String getTagValue(String tag, Element eElement) {

        //결과를 저장할 result 변수 선언
        String result = "";

        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        result = nlList.item(0).getTextContent();

        return result;
    }
    // tag값의 정보를 가져오는 함수

    public static String getTagValue(String tag, String childTag, Element eElement) {

        //결과를 저장할 result 변수 선언
        String result = "";

        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        for(int i = 0; i < eElement.getElementsByTagName(childTag).getLength(); i++) {

            result += nlList.item(i).getChildNodes().item(0).getTextContent() + " ";
        }

        return result;
    }

    public String call(String stockName) throws Exception {
        String serviceKey = "L287ZhVphoAlHm6Qs1C7f3H%2FpwT6%2BYlr4TK7t7AxhoMKsUtThnlwPHpnnYfbpwLomNF6wxNm%2FQh0N8EoRH4Rpw%3D%3D";

        String result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", serviceKey)
                        .queryParam("numOfRows", 10)
                        .queryParam("pageNo", 1)
                        .queryParam("resultType", "json")
                        .queryParam("itmsNm", stockName)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {

            JSONObject jsonObject = new JSONObject(result);
            JSONObject response = jsonObject.getJSONObject("response");
            JSONObject body = response.getJSONObject("body");
            JSONObject items = body.getJSONObject("items");
            JSONArray list = (JSONArray)items.get("item");

            for (int i = 0; i < list.length(); i++) {
                JSONObject item = list.getJSONObject(i);
                String jsonString = item.toString();

                SampleDto sampleDto = objectMapper.readValue(jsonString, SampleDto.class);

                System.out.print(sampleDto.getStockName());
                System.out.print(" | ");
                System.out.print(sampleDto.getBaseDate());
                System.out.print(" | ");
                System.out.print(sampleDto.getVs());
                System.out.print(" | ");
                System.out.print(sampleDto.getMarketClass());
                System.out.print(" | ");
                System.out.print(sampleDto.getIsinCd());
                System.out.print(" | ");
                System.out.print(sampleDto.getClpr());
                System.out.print(" | ");
                System.out.print(sampleDto.getLopr());
                System.out.print(" | ");
                System.out.print(sampleDto.getHipr());
                System.out.print(" | ");
                System.out.print(sampleDto.getMkp());
                System.out.print(" | ");
                System.out.print(sampleDto.getLstgStCnt());
                System.out.print(" | ");
                System.out.print(sampleDto.getMrktTotAmt());
                System.out.print(" | ");
                System.out.print(sampleDto.getTrqu());
                System.out.print(" | ");
                System.out.print(sampleDto.getFltRt());
                System.out.println();
            }

            return body.toString();

        } catch (Exception exception) {

            try {

                Document document = dBuilder.parse(new InputSource(new StringReader(result)));
                document.getDocumentElement().normalize();

                // 읽어들인 파일 불러오기
                NodeList nodes = document.getElementsByTagName("cmmMsgHeader");
                for (int k = 0; k < nodes.getLength(); k++) {
                    Node node = nodes.item(k);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        System.out.println("errMsg: " + getTagValue("errMsg", element));
                        System.out.println("returnAuthMsg: " + getTagValue("returnAuthMsg", element));
                        System.out.println("returnReasonCode: " + getTagValue("returnReasonCode", element));
                    }
                }
            } catch (Exception e) {
                return e.toString();
            }

            return "error";
        }

    }

}

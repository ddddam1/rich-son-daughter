package com.starter.stock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.stock.collection.SearchParam;
import com.starter.stock.collection.Result;
import com.starter.stock.repository.RecordRepository;
import com.starter.stock.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import java.io.StringReader;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class WebService {

    private final WebClient webClient;
    private final DocumentBuilder dBuilder;
    private final ObjectMapper objectMapper;
    private final FormattingConversionService conversionService;
    private final ResponseRepository responseRepository;

    private final RecordRepository rankRepository;

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

    public Result call(SearchParam searchParam) throws Exception {
        String serviceKey = "L287ZhVphoAlHm6Qs1C7f3H%2FpwT6%2BYlr4TK7t7AxhoMKsUtThnlwPHpnnYfbpwLomNF6wxNm%2FQh0N8EoRH4Rpw%3D%3D";

        String result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", serviceKey)
                        .queryParam("numOfRows", 1)
                        .queryParam("pageNo", 1)
                        .queryParam("resultType", "json")
                        .queryParam("itmsNm", URLEncoder.encode(searchParam.getStockName()))
                        .queryParam("basDt", searchParam.getBaseDate())
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

            Result responseDto = new Result();

            for (int i = 0; i < list.length(); i++) {
                JSONObject item = list.getJSONObject(i);
                String jsonString = item.toString();

                responseDto = objectMapper.readValue(jsonString, Result.class);

                responseDto.setStockCount(responseDto.getCAPITAL() / responseDto.getHighPrice());

                responseRepository.save(responseDto);
            }

            return responseDto;

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
                return new Result();//e.toString();
            }
            return new Result();//"error";
        }
    }

    private void consolePrintResult(Result result) {

        System.out.print(result.getStockName());
        System.out.print(" | ");
        System.out.print(result.getBaseDate());
        System.out.print(" | ");
        System.out.print(getFormatted(result.getVersus()));
        System.out.print(" | ");
        System.out.print(getFormatted(result.getClosingPrice()));
        System.out.print(" | ");
        System.out.print(getFormatted(result.getMarketPrice()));
        System.out.print(" | ");
        System.out.print(getFormatted(result.getLowPrice()));
        System.out.print(" | ");
        System.out.print(getFormatted(result.getHighPrice()));
        System.out.print(" | ");
        System.out.print(getFormatted(result.getListedStockCount()));
        System.out.print(" | ");
        System.out.print(getFormatted(result.getMarketTotalAmount()));
        System.out.print(" | ");
        System.out.print(result.getTradeQuantity());
        System.out.print(" | ");
        System.out.print(result.getVersusRate());
        System.out.println();
    }

    private String getFormatted(int price) {
        return conversionService.convert(price, String.class);
    }

    private String getFormatted(long price) {
        return conversionService.convert(price, String.class);
    }
}

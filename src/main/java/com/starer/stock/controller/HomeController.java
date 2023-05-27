package com.starer.stock.controller;

import com.starer.stock.model.RequestDto;
import com.starer.stock.model.ResponseDto;
import com.starer.stock.repository.RequestRepository;
import com.starer.stock.repository.ResponseRepository;
import com.starer.stock.service.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final WebService webService;
    private final RequestRepository requestRepository;
//    private final ResponseRepository responseRepository;

//    @GetMapping("/")
//    public String Home(@ModelAttribute("dto") RequestDto dto) {
//        System.out.println(dto.getPrice());
//        return "home";
//    }

    @GetMapping("/home")
    @ResponseBody
    public String Home(RequestDto requestDto) throws Exception {

        List<ResponseDto> result = requestRepository.findByStockNameAndBaseDate(requestDto.getStockName(), requestDto.getBaseDate());

        if (result.size() > 0) {
            return result.toString();
        }
        requestRepository.save(requestDto);

        return webService.call(requestDto);
    }

    @GetMapping("/jsonapi")
    @ResponseBody
    public String callApiWithJson() {
        //test
        StringBuffer result = new StringBuffer();
        String jsonPrintString = null;
        try {
            String apiUrl = "http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?" +
                    "serviceKey=L287ZhVphoAlHm6Qs1C7f3H%2FpwT6%2BYlr4TK7t7AxhoMKsUtThnlwPHpnnYfbpwLomNF6wxNm%2FQh0N8EoRH4Rpw%3D%3D" +
                    "&numOfRows=10" +
                    "&pageNo=1" +
                    "&resultType=json";
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            jsonPrintString = result.toString();
//            JSONObject jsonObject = XML.toJSONObject(result.toString());
//            jsonPrintString = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonPrintString;
    }
}

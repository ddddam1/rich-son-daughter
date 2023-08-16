package com.starter.stock.controller;

import com.starter.stock.domain.Member;
import com.starter.stock.domain.Ranking;
import com.starter.stock.domain.Record;
import com.starter.stock.collection.SearchParam;
import com.starter.stock.domain.Role;
import com.starter.stock.dto.RequestDto;
import com.starter.stock.collection.Result;
import com.starter.stock.repository.MemberRepository;
import com.starter.stock.repository.RankingRepository;
import com.starter.stock.repository.RecordRepository;
import com.starter.stock.service.SearchService;
import com.starter.stock.service.Util;
import com.starter.stock.service.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StockController {

    private final WebService webService;

    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;
    private final RankingRepository rankingRepository;
    private final SearchService searchService;

    @GetMapping("/home")
    public String Home() {
        return "home";
    }
    @GetMapping("/test")
    public String Test() {
        return "index.html";
    }

    @PostMapping("/")
    @ResponseBody
    public Integer OpenMarket(@RequestBody RequestDto requestDto) throws Exception {
        SearchParam searchParam = new SearchParam();
        searchParam.setBaseDate(requestDto.getBuyDate().replaceAll("-", ""));
        searchParam.setStockName(requestDto.getStockName());

        Result buyResult = searchService.search(searchParam);

        searchParam.setBaseDate(requestDto.getSellDate().replaceAll("-", ""));
        Result sellResult = searchService.search(searchParam);

        Record record = new Record();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        record.setBuyDate(format.parse(buyResult.getBaseDate()));
        record.setSellDate(format.parse(sellResult.getBaseDate()));
        record.setStockName(buyResult.getStockName());
        record.setStockCount(buyResult.getStockCount());
        int income = (sellResult.getHighPrice() - buyResult.getHighPrice()) * sellResult.getStockCount();
        record.setIncome(income);

        List<Record> byStockNameAndDate = recordRepository.findByStockNameAndBuyDateAndSellDate(record.getStockName(), record.getBuyDate(), record.getSellDate());
        if (byStockNameAndDate.size() == 0) {
            recordRepository.save(record);
        }

        Ranking ranking = new Ranking();
        if (byStockNameAndDate.size() == 0) {
            ranking.setRecord(record);
        } else {
            ranking.setRecord(byStockNameAndDate.get(0));
        }
        ranking.setName(Util.randomHangulName());

        rankingRepository.save(ranking);

        Member guest = new Member();
        guest.setName(ranking.getName());
        guest.setRole(Role.GUEST.name());
        guest.setPassword("");
        memberRepository.save(guest);

//        return guest.getMemberId().toString() + ", " + ranking.getName();
        return income;
    }


    @GetMapping("/ranking/list")
    public String Ranking(Model model) {
        List<Record> all = recordRepository.findAll(Sort.by(Sort.Direction.DESC, "income"));
        model.addAttribute("ranking", all);
        return "ranking";
    }

    @ResponseBody
    @GetMapping("/mypage")
    public String MyPage() {

        return "<script>alert('준비중입니다.');history.back();</script>";
    }

    @ResponseBody
    @GetMapping("/contact")
    public String Contact() {

        return "<script>alert('준비중입니다.');history.back();</script>";
    }

    @GetMapping("/jsonapi")
    @ResponseBody
    public String callApiWithJson() {
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

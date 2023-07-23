package com.starter.stock.service;

import com.starter.stock.collection.SearchParam;
import com.starter.stock.collection.Result;
import com.starter.stock.repository.RecordRepository;
//import com.starer.stock.repository.RequestRepository;
import com.starter.stock.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final ResponseRepository responseRepository;

    private final WebService webService;

    public Result search(SearchParam searchParam) throws Exception {

        List<Result> buyResult = responseRepository.findByStockNameAndBaseDate(searchParam.getStockName(), searchParam.getBaseDate());

        if (buyResult.size() > 0) {
            return buyResult.get(0);
        }

        Result apiCallResult = webService.call(searchParam);
        responseRepository.save(apiCallResult);

        return apiCallResult;
    }
}

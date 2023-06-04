package com.starer.stock.service;

import com.starer.stock.collection.Search;
import com.starer.stock.model.ResponseDto;
import com.starer.stock.repository.RankRepository;
//import com.starer.stock.repository.RequestRepository;
import com.starer.stock.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
//    private final RequestRepository requestRepository;
    private final ResponseRepository responseRepository;

    private final RankRepository rankRepository;

    private final WebService webService;

    public ResponseDto search(Search searchParam) throws Exception {

        List<ResponseDto> buyResult = responseRepository.findByStockNameAndBaseDate(searchParam.getStockName(), searchParam.getBaseDate());

        if (buyResult.size() > 0) {
            return buyResult.get(0);
        }

        ResponseDto apiCallResult = webService.call(searchParam);
        responseRepository.save(apiCallResult);

        return apiCallResult;
    }
}

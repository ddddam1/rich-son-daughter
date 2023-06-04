package com.starer.stock.repository;

import com.starer.stock.collection.Search;
import com.starer.stock.model.ResponseDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface ResponseRepository extends MongoRepository<ResponseDto, String> {
    public List<ResponseDto> findByStockNameAndBaseDate(String stockName, String baseDate);

}

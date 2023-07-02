package com.starter.stock.repository;

import com.starter.stock.collection.SearchParam;
import com.starter.stock.collection.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface RequestRepository extends MongoRepository<SearchParam, String> {
    public List<Result> findByStockNameAndBaseDate(String stockName, String baseDate);
}

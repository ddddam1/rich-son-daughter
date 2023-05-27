package com.starer.stock.repository;

import com.starer.stock.collection.Customer;
import com.starer.stock.model.RequestDto;
import com.starer.stock.model.ResponseDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface ResponseRepository extends MongoRepository<ResponseDto, String> {

}

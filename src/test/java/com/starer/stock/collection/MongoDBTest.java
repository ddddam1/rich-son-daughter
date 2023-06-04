package com.starer.stock.collection;

//import com.starer.stock.repository._CustomerRepository;
import com.starer.stock.model.ResponseDto;
import com.starer.stock.repository.RequestRepository;
import com.starer.stock.repository.ResponseRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
public class MongoDBTest {

//    @Autowired
//    private _CustomerRepository customerRepository;
//
//    @Test
//    void testSave() {
//        _Customer customer = new _Customer("kim", "sodam");
//        customerRepository.save(customer);
//    }

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void test() {
        Search param = new Search();
//        ResponseDto param = new ResponseDto();
        param.setBaseDate("20230320");
        param.setStockName("삼성전자");
        List<ResponseDto> byStockNameAndBaseDate = responseRepository.findByStockNameAndBaseDate(param.getStockName(), param.getBaseDate());
        Assertions.assertEquals(1, byStockNameAndBaseDate.size());
    }

    @Test
    public void Test2() {
        Search param = new Search();
        param.setBaseDate("20230327");
        param.setStockName("삼성전자");

        List<ResponseDto> result = requestRepository.findByStockNameAndBaseDate(param.getStockName(), param.getBaseDate());

        List<Search> all = requestRepository.findAll();
        Assertions.assertEquals(1, all.size());
    }

//    @Configuration(proxyBeanMethods = false)
//    class TestConfig {
//
//        @Bean("testMongoTemplate") // testMongoTemplate Bean 설정 및 선언하기
//        public MongoTemplate mongoTemplateConfig() {
//            MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
//            return new MongoTemplate(mongoClient, "mser");
//        }
//    }

}

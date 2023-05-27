package com.starer.stock.collection;

import com.starer.stock.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class MongoDBTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSave() {
        Customer customer = new Customer("kim", "sodam");
        customerRepository.save(customer);
    }

}

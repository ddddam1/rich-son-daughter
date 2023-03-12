package com.starer.stock.controller;

//import com.starer.stock.service.TestService;
import com.starer.stock.collection.Customer;
import com.starer.stock.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MongoDBController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/insert/{name}/{city}")
    public String insert(@RequestParam("name") String name, @RequestParam("city") String city) {

        Customer customer = new Customer(name, city);
        customerRepository.save(customer);
//        mongoService.insert(name, city);
        return "ok";
    }
}

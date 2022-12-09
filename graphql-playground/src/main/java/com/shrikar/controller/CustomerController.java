package com.shrikar.controller;


import com.shrikar.dto.AgeRangeFilter;
import com.shrikar.dto.Customer;
import com.shrikar.dto.CustomerOrderDto;
import com.shrikar.service.CustomerService;
import com.shrikar.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private OrderService orderService;

    @QueryMapping
    public Flux<Customer> customers(){
        return this.service.allCustomers();
    }

    @QueryMapping
    public Mono<Customer> customerById(@Argument Integer id){
        return this.service.customerById(id);
    }

    @QueryMapping
    public Flux<Customer> customersNameContains(@Argument String name){
        return this.service.nameContains(name);
    }

    @QueryMapping
    public Flux<Customer> customersByAgeRange(@Argument AgeRangeFilter filter){
        return this.service.withinAge(filter);
    }

    @SchemaMapping(typeName = "Query")
    public Flux<Customer> customerso(){
        return this.service.allCustomers();
    }

    @SchemaMapping(typeName = "Customer")
    public Flux<CustomerOrderDto> orders(Customer customer, @Argument Integer limit){
        System.out.println("Orders method invoked for " + customer.getName());
        return this.orderService.ordersByCustomerName(customer.getName())
                .take(limit);
    }
}

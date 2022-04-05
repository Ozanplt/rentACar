package com.etiya.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import com.etiya.rentACar.business.requests.customerRequests.DeleteCustomerRequest;
import com.etiya.rentACar.business.requests.customerRequests.UpdateCustomerRequest;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACar.business.abstracts.CustomerService;
import com.etiya.rentACar.business.requests.customerRequests.CreateCustomerRequest;

import com.etiya.rentACar.business.responses.customerResponses.ListCustomerDto;

import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
@RestController
@RequestMapping("/api/customers")
public class CustomersController {
    private CustomerService customerService;


    public CustomersController(CustomerService customerService) {

        this.customerService = customerService;
    }


    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
        return this.customerService.add(createCustomerRequest);
    }


    @GetMapping("/getall")
    public DataResult<List<ListCustomerDto>> getAll() {
        return customerService.getAll();
    }

    @PutMapping("/update")
    public Result update(UpdateCustomerRequest updateCustomerRequest){
        return this.customerService.update(updateCustomerRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(DeleteCustomerRequest deleteCustomerRequest){
        return this.customerService.delete(deleteCustomerRequest);
    }
}
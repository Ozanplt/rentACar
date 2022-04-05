package com.etiya.rentACar.api.controllers;


import com.etiya.rentACar.business.abstracts.OrderedAdditionalPropertyService;
import com.etiya.rentACar.business.requests.orderedAdditionalPropertyRequests.CreateOrderedAdditionalPropertyRequest;
import com.etiya.rentACar.business.responses.orderedAdditionalPropertyResponses.ListOrderedAdditionalPropertyDto;
import com.etiya.rentACar.business.responses.orderedAdditionalPropertyResponses.OrderedAdditionalPropertyDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderedadditionalproperties")
public class OrderedAdditionalPropertiesController {

   private OrderedAdditionalPropertyService orderedAdditionalPropertyService;

    public OrderedAdditionalPropertiesController(OrderedAdditionalPropertyService orderedAdditionalPropertyService) {
        this.orderedAdditionalPropertyService = orderedAdditionalPropertyService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateOrderedAdditionalPropertyRequest createOrderedAdditionalPropertyRequest){
        return this.orderedAdditionalPropertyService.add(createOrderedAdditionalPropertyRequest);
    }
    @GetMapping("/getall")
    public DataResult<List<ListOrderedAdditionalPropertyDto>> getAll(){
        return this.orderedAdditionalPropertyService.getAll();
    }
}

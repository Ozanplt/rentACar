package com.etiya.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import com.etiya.rentACar.business.requests.rentalRequests.DifferentRentDeliveryCityRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateReturnDateRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

    private RentalService rentalService;

    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
        return this.rentalService.add(createRentalRequest);
    }


    @GetMapping("/getall")
    public DataResult<List<ListRentalDto>> getAll() {
        return rentalService.getAll();
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {

        return this.rentalService.update(updateRentalRequest);
    }

    @PutMapping("/updatereturndate")
    public Result updateRentalReturnDate(@RequestBody UpdateReturnDateRequest updateReturnDateRequest){

        return this.rentalService.updateRentalReturnDate(updateReturnDateRequest);
    }

//    @PostMapping("/latefee")
//    public Result lateFee(@RequestBody DifferentRentDeliveryCityRequest differentRentDeliveryCityRequest){
//
//        return this.rentalService.lateFee(differentRentDeliveryCityRequest);
//    }

}
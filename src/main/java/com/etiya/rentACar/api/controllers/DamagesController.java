package com.etiya.rentACar.api.controllers;


import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/damages")
public class DamagesController {

    private DamageService damageService;

    public DamagesController(DamageService damageService) {
        this.damageService = damageService;
    }
    @PostMapping("/add")
    public Result add(@RequestBody CreateDamageRequest createDamageRequest){

        return this.damageService.add(createDamageRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListDamageDto>> getAll() {
        return this.damageService.getAll();
    }

    @GetMapping("/get")
    public DataResult<List<ListDamageDto>> getByCarId(@RequestParam int id){
        return this.damageService.getByCarId(id);
    }
    @GetMapping("/get/getallpaged")
    public DataResult<List<ListDamageDto>> getAllPaged(@RequestParam int pageNo,@RequestParam int pageSize) {
        return this.damageService.getAllPaged(pageNo, pageSize);


    }
    @GetMapping("/get/getallsorted")
    public DataResult<List<ListDamageDto>> getAllSorted(@RequestParam String field,@RequestParam String option){
        return this.damageService.getAllSorted(field,option);

    }

}
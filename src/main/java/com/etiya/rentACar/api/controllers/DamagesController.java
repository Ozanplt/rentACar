package com.etiya.rentACar.api.controllers;


import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;
import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/damages")
public class DamagesController {

    private DamageService damageService;

    public DamagesController(DamageService damageService) {
        this.damageService = damageService;
    }


    @GetMapping("/getall")
    public DataResult<List<ListDamageDto>> getAll() {
        return this.damageService.getAll();
    }

    @GetMapping("/getallbycarid")
    public DataResult<List<ListDamageDto>> getAllByCarId(@RequestParam int id) {
        return this.damageService.getAllByCarId(id);
    }

    @GetMapping("/get/getallpaged")
    public DataResult<List<ListDamageDto>> getAllPaged(@RequestParam int pageNo, @RequestParam int pageSize) {

        return this.damageService.getAllPaged(pageNo, pageSize);

    }

    @GetMapping("/get/getallsorted")
    public DataResult<List<ListDamageDto>> getAllSorted(@RequestParam String field, @RequestParam String option) {

        return this.damageService.getAllSorted(field, option);

    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateDamageRequest createDamageRequest) {

        return this.damageService.add(createDamageRequest);
    }


    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateDamageRequest updateDamageRequest) {
        return this.damageService.update(updateDamageRequest);
    }


    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteDamageRequest deleteDamageRequest) {
        return this.damageService.delete(deleteDamageRequest);
    }

}
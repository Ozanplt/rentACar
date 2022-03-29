package com.etiya.rentACar.api.controllers;


import org.springframework.web.bind.annotation.*;
import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;

import java.util.List;

@RestController
@RequestMapping("/api/damages")
public class DamagesController {

    private DamageService damageService;

    public DamagesController(DamageService damageService) {
        this.damageService = damageService;
    }


    @GetMapping("/getall")
    public List<ListDamageDto> getAll() {
        return this.damageService.getAll();
    }

    @GetMapping("/getallbycarid")
    public List<ListDamageDto> getAllByCarId(@RequestParam int id) {
        return this.damageService.getAllByCarId(id);
    }

    @GetMapping("/get/getallpaged")
    public List<ListDamageDto> getAllPaged(@RequestParam int pageNo, @RequestParam int pageSize) {

        return this.damageService.getAllPaged(pageNo, pageSize);

    }

    @GetMapping("/get/getallsorted")
    public List<ListDamageDto> getAllSorted(@RequestParam String field, @RequestParam String option) {

        return this.damageService.getAllSorted(field, option);

    }

    @PostMapping("/add")
    public void add(@RequestBody CreateDamageRequest createDamageRequest) {

        this.damageService.add(createDamageRequest);
    }


    @PutMapping("/update")
    public void update(@RequestBody UpdateDamageRequest updateDamageRequest) {
        this.damageService.update(updateDamageRequest);
    }


    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteDamageRequest deleteDamageRequest) {
        this.damageService.delete(deleteDamageRequest);
    }

}
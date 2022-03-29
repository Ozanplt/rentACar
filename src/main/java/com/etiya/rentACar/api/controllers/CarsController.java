package com.etiya.rentACar.api.controllers;

import org.springframework.web.bind.annotation.*;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/getall")
    public List<ListCarDto> getAll() {
        return this.carService.getAll();
    }

    @GetMapping("/getallbymodelyear")
    public List<ListCarDto> getAllByModelYear(@RequestParam("modelYear") double modelYear) {
        return this.carService.getAllByModelYear(modelYear);
    }

    @GetMapping("/getallpaged")
    public List<ListCarDto> getAllPaged(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return this.carService.getAllPaged(pageNo, pageSize);

    }

    @GetMapping("/getallsorted")
    public List<ListCarDto> getAllSorted() {
        return this.carService.getAllSorted();
    }

    @GetMapping("/getbyid")
    public CarDto getById(@RequestParam int id) {
        return this.carService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateCarRequest createCarRequest) {
        this.carService.add(createCarRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateCarRequest updateCarRequest) {
        this.carService.update(updateCarRequest);
    }


    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteCarRequest deleteCarRequest) {
        this.carService.delete(deleteCarRequest);
    }




}

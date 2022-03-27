package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateCarRequest createCarRequest) {

        return this.carService.add(createCarRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListCarDto>> getAll(){
        return this.carService.getAll();
    }

    @GetMapping("/getallbymodelyear")
    public DataResult<List<ListCarDto>> getAllByModelYear(@RequestParam("modelYear") double modelYear){
        return this.carService.getAllByModelYear(modelYear);
    }

    @GetMapping("/getallpaged")
    public DataResult<List<ListCarDto>> getAllPaged(int pageNo,int pageSize){
        return this.carService.getAllPaged(pageNo, pageSize);

    }
    @GetMapping("/getallsorted")
    public DataResult<List<ListCarDto>> getAllSorted(){
        return this.carService.getAllSorted();
    }
}

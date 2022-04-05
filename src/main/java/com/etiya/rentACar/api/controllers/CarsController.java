package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.requests.carRequests.UpdateCarStatusRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/getall")
    public DataResult<List<ListCarDto>> getAll() {
        return this.carService.getAll();
    }

    @GetMapping("/getallbymodelyear")
    public DataResult<List<ListCarDto>> getAllByModelYear(@RequestParam("modelYear") double modelYear) {
        return this.carService.getAllByModelYear(modelYear);
    }
    @PutMapping("/updatecarstatus")
    public DataResult<CarDto> updateCarStatus(@RequestBody @Valid UpdateCarStatusRequest updateCarStatusRequest){
        return this.carService.updateCarStatus(updateCarStatusRequest);
    }

    @GetMapping("/getallpaged")
    public DataResult<List<ListCarDto>> getAllPaged(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return this.carService.getAllPaged(pageNo, pageSize);

    }

    @GetMapping("/getallsorted")
    public DataResult<List<ListCarDto>> getAllSorted() {
        return this.carService.getAllSorted();
    }

    @GetMapping("/getbycarid")
    public DataResult<CarDto> getByCarId(@RequestParam int id) {
        return this.carService.getByCarId(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
        return this.carService.add(createCarRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
        return this.carService.update(updateCarRequest);
    }



    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
        return this.carService.delete(deleteCarRequest);
    }

    @GetMapping("/getallbycity")
    public DataResult<List<ListCarDto>> getByCityId(@RequestParam ("City Id") int cityId){
        return this.carService.getByCityId(cityId);
    }



}

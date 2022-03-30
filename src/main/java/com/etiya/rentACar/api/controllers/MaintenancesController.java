package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;
import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {


    private MaintenanceService maintenanceService;


    public MaintenancesController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }


    @GetMapping("/getall")
    public DataResult<List<ListMaintenanceDto>> getAll() {

        return this.maintenanceService.getAll();
    }

    @GetMapping("/getbycarid")
    public DataResult<List<ListMaintenanceDto>> getByCarId(@RequestParam int id) {
        return this.maintenanceService.getByCarId(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateMaintenanceRequest createMaintenanceRequest){

        return this.maintenanceService.add(createMaintenanceRequest);

    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateMaintenanceRequest updateMaintenanceRequest) {
        return this.maintenanceService.update(updateMaintenanceRequest);
    }


    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteMaintenanceRequest deleteMaintenanceRequest) {
       return this.maintenanceService.delete(deleteMaintenanceRequest);
    }


}
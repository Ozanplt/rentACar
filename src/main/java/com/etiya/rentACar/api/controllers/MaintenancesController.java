package com.etiya.rentACar.api.controllers;

import org.springframework.web.bind.annotation.*;
import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;

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
    public List<ListMaintenanceDto> getAll() {

        return this.maintenanceService.getAll();
    }

    @GetMapping("/getbycarid")
    public List<ListMaintenanceDto> getByCarId(@RequestParam int id) {
        return this.maintenanceService.getByCarId(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest){

        this.maintenanceService.add(createMaintenanceRequest);

    }
    @PutMapping("/update")
    public void update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
        this.maintenanceService.update(updateMaintenanceRequest);
    }


    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteMaintenanceRequest deleteMaintenanceRequest) {
        this.maintenanceService.delete(deleteMaintenanceRequest);
    }


}
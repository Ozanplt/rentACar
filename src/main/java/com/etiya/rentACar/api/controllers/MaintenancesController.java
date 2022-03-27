package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {


    private MaintenanceService maintenanceService;


    public MaintenancesController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) throws ParseException {
        return this.maintenanceService.add(createMaintenanceRequest);

    }

    @GetMapping("/getall")
    public DataResult<List<ListMaintenanceDto>> getAll(){
        return this.maintenanceService.getAll();
    }
    @GetMapping("/getByCarId")
    public DataResult<List<ListMaintenanceDto>> getByCarId(@RequestParam int id){
        return this.maintenanceService.getByCarId(id);
    }


    /*@GetMapping("/getstatement")
    public String getMaintenanceByStatement(int carId){
        return this.maintenanceService.getMaintenanceByStatement();
    }*/

}
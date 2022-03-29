package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;

import java.text.ParseException;
import java.util.List;

public interface MaintenanceService {


    List<ListMaintenanceDto> getAll();
    List<ListMaintenanceDto> getByCarId(int id);

    void add(CreateMaintenanceRequest createMaintenanceRequest);
    void delete(DeleteMaintenanceRequest deleteMaintenanceRequest);
    void update(UpdateMaintenanceRequest updateMaintenanceRequest);

}
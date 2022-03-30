package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.text.ParseException;
import java.util.List;

public interface MaintenanceService {


    DataResult<List<ListMaintenanceDto>> getAll();
    DataResult<List<ListMaintenanceDto>> getByCarId(int id);

    Result add(CreateMaintenanceRequest createMaintenanceRequest);
    Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);
    Result update(UpdateMaintenanceRequest updateMaintenanceRequest);

}
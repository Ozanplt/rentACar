package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;

import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.text.ParseException;
import java.util.List;

public interface MaintenanceService {

    Result add(CreateMaintenanceRequest createMaintenanceRequest) throws ParseException;
    DataResult<List<ListMaintenanceDto>> getAll();
    DataResult<List<ListMaintenanceDto>> getByCarId(int id);

}
package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DifferentRentDeliveryCityRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import lombok.Data;

public interface RentalService {
    DataResult<List<ListRentalDto>> getAll();
    Result add(CreateRentalRequest createRentalRequest);


    Result update(UpdateRentalRequest updateRentalRequest);
}
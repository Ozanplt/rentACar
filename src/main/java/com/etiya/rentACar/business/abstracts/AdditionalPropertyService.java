package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.additionalPropertyRequests.CreateAdditionalPropertyRequest;
import com.etiya.rentACar.business.requests.additionalPropertyRequests.DeleteAdditionalPropertyRequest;
import com.etiya.rentACar.business.requests.additionalPropertyRequests.UpdateAdditionalPropertyRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.AdditionalPropertyDto;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.ListAdditionalPropertyDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface AdditionalPropertyService {



    DataResult<List<ListAdditionalPropertyDto>> getAll();
    ListAdditionalPropertyDto getById(int id);

    Result add(CreateAdditionalPropertyRequest createAdditionalPropertyRequest);
    Result update(UpdateAdditionalPropertyRequest updateAdditionalPropertyRequest);
    Result delete(DeleteAdditionalPropertyRequest deleteAdditionalPropertyRequest);


}

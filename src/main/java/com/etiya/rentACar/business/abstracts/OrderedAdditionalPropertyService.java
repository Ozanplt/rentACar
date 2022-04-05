package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.orderedAdditionalPropertyRequests.CreateOrderedAdditionalPropertyRequest;
import com.etiya.rentACar.business.responses.orderedAdditionalPropertyResponses.ListOrderedAdditionalPropertyDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;



public interface OrderedAdditionalPropertyService {
    Result add(CreateOrderedAdditionalPropertyRequest createOrderedAdditionalPropertyRequest);
    DataResult<List<ListOrderedAdditionalPropertyDto>> getAll();
    ListOrderedAdditionalPropertyDto getById(int id);
    List<ListOrderedAdditionalPropertyDto> getAllIdById(int id);
}

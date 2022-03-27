package com.etiya.rentACar.business.abstracts;


import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CarService {
    Result add(CreateCarRequest createCarRequest);
    DataResult<List<ListCarDto>> getAll();
    DataResult<List<ListCarDto>> getAllByModelYear(double modelYear);
    DataResult<List<ListCarDto>> getAllPaged(int pageNo,int pageSize);
    DataResult<List<ListCarDto>> getAllSorted();
}

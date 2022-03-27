package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;


import java.util.List;

public interface DamageService {
    Result add(CreateDamageRequest createDamageRequest);
    DataResult<List<ListDamageDto>> getAll();

    DataResult<List<ListDamageDto>> getByCarId(int id);
    DataResult<List<ListDamageDto>> getAllPaged(int pageNo,int pageSize);
    DataResult<List<ListDamageDto>> getAllSorted(String field,String option);

}

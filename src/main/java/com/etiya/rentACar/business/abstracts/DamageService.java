package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.DamageDto;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;

import java.util.List;

public interface DamageService {

    List<ListDamageDto> getAll();
    List<ListDamageDto> getAllByCarId(int carId);
    List<ListDamageDto> getAllPaged(int pageNo, int pageSize);
    List<ListDamageDto> getAllSorted(String field, String option);

    DamageDto getById(int id);

     void add(CreateDamageRequest createDamageRequest);
     void delete(DeleteDamageRequest deleteDamageRequest);
     void update(UpdateDamageRequest updateDamageRequest);


}

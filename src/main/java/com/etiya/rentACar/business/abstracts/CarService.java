package com.etiya.rentACar.business.abstracts;


import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStatusRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CarService {


    DataResult<List<ListCarDto>> getAll();

    DataResult<List<ListCarDto>> getAllByModelYear(double modelYear);

    DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<ListCarDto>> getAllSorted();

    DataResult<CarDto> updateCarStatus(UpdateCarStatusRequest updateCarStatusRequest);

    CarDto getById(int id);

    DataResult<CarDto> getByCarId(int id);

    Result add(CreateCarRequest createCarRequest);

    Result update(UpdateCarRequest updateCarRequest);

    Result delete(DeleteCarRequest deleteCarRequest);

    void updateCarStatusToAdd(int id);

    void checkIfCarAvailable(int id);

    DataResult<List<ListCarDto>> getByCityId(int id);

    CarDto getCarKilometer(int id);

    void setCarKilometer(CreateRentalRequest createRentalRequest);

}

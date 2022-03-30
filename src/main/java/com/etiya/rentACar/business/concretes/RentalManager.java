package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.RentalDao;
import com.etiya.rentACar.entities.Rental;

@Service
public class RentalManager implements RentalService {
    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;
    private CarService carService;

    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService) {
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {

        carService.checkIfCarAvailable(createRentalRequest.getCarId());


        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rental.setReturnDate(null);
        this.rentalDao.save(rental);

        return new SuccessResult("RENTAL_CAR_ADDED");
    }

    @Override
    public DataResult<List<ListRentalDto>> getAll() {

        List<Rental> rentals = this.rentalDao.findAll();
        List<ListRentalDto> response = rentals.stream()
                .map(car -> this.modelMapperService.forDto().map(car, ListRentalDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListRentalDto>>(response);
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.rentalDao.save(rental);

        return new SuccessResult("RENTAL_CAR_UPDATED");
    }

}
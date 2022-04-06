package com.etiya.rentACar.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.abstracts.*;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStatusRequest;
import com.etiya.rentACar.business.requests.rentalRequests.*;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.ListAdditionalPropertyDto;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.customerResponses.CustomerDto;
import com.etiya.rentACar.business.responses.invoiceResponses.InvoiceDto;
import com.etiya.rentACar.business.responses.orderedAdditionalPropertyResponses.ListOrderedAdditionalPropertyDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.entities.CarStates;
import com.etiya.rentACar.entities.Customer;
import org.springframework.stereotype.Service;

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
    private AdditionalPropertyService additionalPropertyService;
    private OrderedAdditionalPropertyService orderedAdditionalPropertyService;
    private InvoiceService invoiceService;

    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService, AdditionalPropertyService additionalPropertyService, OrderedAdditionalPropertyService orderedAdditionalPropertyService, InvoiceService invoiceService) {
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.additionalPropertyService = additionalPropertyService;
        this.orderedAdditionalPropertyService = orderedAdditionalPropertyService;
        this.invoiceService = invoiceService;
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {

        carService.checkIfCarAvailable(createRentalRequest.getCarId());

        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);

        this.rentalDao.save(rental);

//        updateKilometer(createRentalRequest);
//        lastKilometer(createRentalRequest);
        updateCarStatusAsRented(createRentalRequest);
        return new SuccessResult("RENTAL_CAR_ADDED");
    }

    //return kilometer rental addde null girilmelidir.update'de doğru değer girilebilir.
    //orderaddtional service ayrıca alınmalı


    @Override
    public DataResult<List<ListRentalDto>> getAll() {

        List<Rental> rentals = this.rentalDao.findAll();
        List<ListRentalDto> response = rentals.stream()
                .map(car -> this.modelMapperService.forDto().map(car, ListRentalDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListRentalDto>>(response);
    }

    public Result updateRentalReturnDate(UpdateReturnDateRequest updateReturnDateRequest) {

        Rental rental = this.rentalDao.getByCarId(updateReturnDateRequest.getCarId());
        rental.setReturnDate(updateReturnDateRequest.getReturnDate());
        rentalDao.save(rental);
        return new SuccessResult("Rental Return Date Updated");
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
//        CreateRentalRequest createRentalRequest = this.modelMapperService.forRequest().map(updateRentalRequest, CreateRentalRequest.class);
        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.rentalDao.save(rental);
        return new SuccessResult("RENTAL_CAR_UPDATED");
    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        this.rentalDao.deleteById(deleteRentalRequest.getId());
        return new SuccessResult("Rental deleted");
    }



    public void updateCarStatusAsRented(CreateRentalRequest createRentalRequest) {
        UpdateCarStatusRequest updateCarStatusRequest = new UpdateCarStatusRequest();
        updateCarStatusRequest.setId(createRentalRequest.getCarId());
        updateCarStatusRequest.setStatus(CarStates.Rented);
        carService.updateCarStatus(updateCarStatusRequest);

    }


    public DataResult<RentalDto> getById(int id){
        Rental rental = this.rentalDao.getById(id);
        RentalDto rentalDto = this.modelMapperService.forDto().map(rental,RentalDto.class);
        return new SuccessDataResult<RentalDto>(rentalDto);
    }
//indirimli metod


    public void updateKilometer(CreateRentalRequest createRentalRequest) {
        CarDto carDto = this.carService.getCarKilometer(createRentalRequest.getCarId());
        Rental rental = this.rentalDao.getByCarId(createRentalRequest.getCarId());
        rental.setStartKilometer(carDto.getCarKilometer());

    }

    public Result lastKilometer(CreateRentalRequest createRentalRequest) {
        this.carService.setCarKilometer(createRentalRequest);
        return new SuccessResult("Arabanın kilometresi güncellendi");
    }

    @Override
    public DataResult<RentalDto> getByLastRental() {
        List<Rental> rentals = this.rentalDao.findAll();
        Rental rental = rentals.get(rentals.size() - 1);
        RentalDto rentalDto = this.modelMapperService.forDto().map(rental, RentalDto.class);
        return new SuccessDataResult<RentalDto>(rentalDto);
    }
}
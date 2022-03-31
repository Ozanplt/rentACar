package com.etiya.rentACar.business.concretes;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStatusRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DifferentRentDeliveryCityRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.entities.CarStates;
import org.apache.tomcat.jni.Local;
import org.hibernate.sql.Update;
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

        addTotalPrice(createRentalRequest);
        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rental.setReturnDate(null);
        this.rentalDao.save(rental);
        CarDto car = this.carService.getById(createRentalRequest.getCarId());
        UpdateCarStatusRequest updateCarStatusRequest=new UpdateCarStatusRequest();
        updateCarStatusRequest.setId(createRentalRequest.getCarId());
        updateCarStatusRequest.setStatus(CarStates.Rented);
        carService.updateCarStatus(updateCarStatusRequest);
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
        CreateRentalRequest createRentalRequest = this.modelMapperService.forRequest().map(updateRentalRequest,CreateRentalRequest.class);
        addTotalPrice(createRentalRequest);
        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.rentalDao.save(rental);

        return new SuccessResult("RENTAL_CAR_UPDATED");
    }



    public void addTotalPrice(CreateRentalRequest createRentalRequest){

        DifferentRentDeliveryCityRequest differentRentDeliveryCityRequest = new DifferentRentDeliveryCityRequest();
        differentRentDeliveryCityRequest.setRentCity(createRentalRequest.getRentCity());
        differentRentDeliveryCityRequest.setDeliveryCity(createRentalRequest.getDeliveryCity());
        differentRentDeliveryCityRequest.setCarId(createRentalRequest.getCarId());
        if(checkCity(differentRentDeliveryCityRequest)) {
            Rental rental = this.rentalDao.getById(differentRentDeliveryCityRequest.getCarId());
            CarDto carDto = this.carService.getById(differentRentDeliveryCityRequest.getCarId());
            if (createRentalRequest.getReturnDate() != null) {
                rental.setTotalPrice(diffDates(createRentalRequest) * carDto.getDailyPrice() + createRentalRequest.getLateFee());
                rental.setTotalPrice(selectProperty());
            }
            else{
                throw new BusinessException(BusinessMessages.RentalMessage.RENTAL);
            }
        }

    }
    public long diffDates(CreateRentalRequest createRentalRequest) {

            long period = ChronoUnit.DAYS.between(createRentalRequest.getReturnDate(), createRentalRequest.getRentDate());
            return period;

    }


    public boolean checkCity(DifferentRentDeliveryCityRequest differentRentDeliveryCityRequest){
        if(differentRentDeliveryCityRequest.getRentCity()!=differentRentDeliveryCityRequest.getDeliveryCity()){

            return true;
        }
        else{
            return false;
        }
    }
}
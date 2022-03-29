package com.etiya.rentACar.business.concretes;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.CarDao;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.CarStates;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarManager implements CarService {
    private CarDao carDao;
    private ModelMapperService modelMapperService;


    public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;

    }

    @Override
    public void add(CreateCarRequest createCarRequest) {
        checkDailyPrice(createCarRequest.getDailyPrice());
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);

    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {

        Car car = this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
        this.carDao.save(car);
    }

    @Override
    public void delete(DeleteCarRequest deleteCarRequest) {
        this.carDao.deleteById(deleteCarRequest.getId());
    }


    @Override
    public List<ListCarDto> getAll() {

        List<Car> cars = this.carDao.findAll();
        List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public CarDto getById(int id) {
        Car car = this.carDao.getById(id);
        CarDto response = this.modelMapperService.forDto().map(car, CarDto.class);
        return response;
    }

    public void updateCarStatus(int id) {
        Car car = this.carDao.getById(id);
        car.setCarState(CarStates.UnderMaintenance);
        this.carDao.save(car);
    }


    @Override
    public List<ListCarDto> getAllByModelYear(double modelYear) {
        List<Car> cars = this.carDao.getByModelYear(modelYear);
        List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListCarDto> getAllPaged(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Car> cars = this.carDao.findAll(pageable).getContent();

        List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListCarDto> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC, "modelYear");

        List<Car> cars = this.carDao.findAll(sort);

        List<ListCarDto> response = cars.stream()
                .map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return response;

    }

    public void checkDailyPrice(double dailyPrice) {
        if (dailyPrice < 50) {
            throw new BusinessException("Fiyat 50'den küçük olamaz");
        }
    }
}

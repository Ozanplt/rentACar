package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.responses.carResponses.CarDto;
import org.springframework.stereotype.Service;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.MaintenanceDao;
import com.etiya.rentACar.entities.Maintenance;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MaintenanceManager implements MaintenanceService {

    private MaintenanceDao maintenanceDao;
    private ModelMapperService modelMapperService;
    private CarService carService;

    public MaintenanceManager(MaintenanceDao maintenanceDao, ModelMapperService modelMapperService, CarService carService) {
        this.maintenanceDao = maintenanceDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public void add(CreateMaintenanceRequest createMaintenanceRequest) {
        checkCarStatus(createMaintenanceRequest.getReturnDate(), createMaintenanceRequest.getCarId());
        Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
        this.maintenanceDao.save(maintenance);
        carService.updateCarStatus(createMaintenanceRequest.getCarId());
    }

    @Override
    public void delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
        this.maintenanceDao.deleteById(deleteMaintenanceRequest.getId());
    }

    @Override
    public void update(UpdateMaintenanceRequest updateMaintenanceRequest) {
        checkCarStatus(updateMaintenanceRequest.getReturnDate(), updateMaintenanceRequest.getCarId());
        Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
        this.maintenanceDao.save(maintenance);
    }

    @Override
    public List<ListMaintenanceDto> getAll() {
        List<Maintenance> maintenances = this.maintenanceDao.findAll();
        List<ListMaintenanceDto> response = maintenances.stream()
                .map(maintenance -> this.modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListMaintenanceDto> getByCarId(int carId) {
        List<Maintenance> maintenances = this.maintenanceDao.getByCarId(carId);
        List<ListMaintenanceDto> response = maintenances.stream()
                .map(maintenance -> this.modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
                .collect(Collectors.toList());
        return response;
    }

    public boolean checkIfCarInMaintenance(int carId) {
        if (maintenanceDao.existsMaintenanceByCarId(carId)) {
            throw new BusinessException("Araba zaten bakımda");
        }
        else{
            return false;
        }
    }

    public void checkMaintenanceDate(LocalDate dateReturned){

//        Date dateOfToday = dateTodayService.getTodayTime();
//      Date newDateAdded = formatDateService.getFormattedDate(dateAdded);
//        Date newDateReturned = formatDateService.getFormattedDate(dateReturned);

        LocalDateTime dateOfToday = LocalDateTime.now();
        if (dateReturned.isBefore(ChronoLocalDate.from(dateOfToday))) {
            throw new BusinessException("Tarihler Uygun Değil.");
        }
    }

    public void checkCarStatus(LocalDate dateReturned, int carId){
        if (!checkIfCarInMaintenance(carId)) {
            checkMaintenanceDate(dateReturned);
        }
    }
}
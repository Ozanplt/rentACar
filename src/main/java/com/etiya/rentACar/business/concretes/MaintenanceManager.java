package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.abstracts.StatementService;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.statementRequests.CreateStatementRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.business.responses.statementResponses.ListStatementDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.dating.DateTodayService;

import com.etiya.rentACar.core.utilities.dating.FormatDateService;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.*;
import com.etiya.rentACar.dataAccess.abstracts.MaintenanceDao;
import com.etiya.rentACar.entities.Maintenance;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MaintenanceManager implements MaintenanceService {

    private MaintenanceDao maintenanceDao;
    private ModelMapperService modelMapperService;
    private DateTodayService dateTodayService;
    private FormatDateService formatDateService;
    private StatementService statementService;

    public MaintenanceManager(MaintenanceDao maintenanceDao, ModelMapperService modelMapperService, DateTodayService dateTodayService, FormatDateService formatDateService, StatementService statementService) {
        this.maintenanceDao = maintenanceDao;
        this.modelMapperService = modelMapperService;
        this.dateTodayService = dateTodayService;
        this.formatDateService = formatDateService;
        this.statementService = statementService;
    }

    @Override
    public Result add(CreateMaintenanceRequest createMaintenanceRequest) throws ParseException {
        checkCarStatement(createMaintenanceRequest.getDateAdded(), createMaintenanceRequest.getDateReturned(), createMaintenanceRequest.getCarId());
        Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
        this.maintenanceDao.save(maintenance);
        return new SuccessResult("bakım başarıyla eklendi");
    }

    @Override
    public DataResult<List<ListMaintenanceDto>> getAll() {
        List<Maintenance> maintenances = this.maintenanceDao.findAll();
        List<ListMaintenanceDto> response = maintenances.stream()
                .map(maintenance -> this.modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListMaintenanceDto>>(response,"bakım listesi başarıyla getirildi");
    }

    @Override
    public DataResult<List<ListMaintenanceDto>> getByCarId(int id) {
        List<Maintenance> maintenances = this.maintenanceDao.findAll();
        List<ListMaintenanceDto> response = maintenances.stream()
                .map(maintenance -> this.modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
                .filter(listMaintenanceDto -> listMaintenanceDto.getCarId() == id)
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListMaintenanceDto>>(response,"bakım listesi id'ye göre getirildi");
    }

    public boolean checkIfCarInMaintenance(int carId) {
        return maintenanceDao.existsMaintenanceByCarId(carId);
    }

    public void checkMaintenanceDate(String dateAdded, String dateReturned) throws ParseException {
        Date dateOfToday = dateTodayService.getTodayTime();
        Date newDateAdded = formatDateService.getFormattedDate(dateAdded);
        Date newDateReturned = formatDateService.getFormattedDate(dateReturned);

        if (newDateAdded.before(dateOfToday) && newDateReturned.after(dateOfToday)) {
            //Araba bakımda değil. Araba uygun
        }
        else {
            throw new RuntimeException("Tarihler Uygun Değil.");
        }
    }

    public void checkCarStatement(String dateAdded, String dateReturned, int carId) throws ParseException {
        if (!checkIfCarInMaintenance(carId)) {
            checkMaintenanceDate(dateAdded, dateReturned);
        }
        else{
            throw new BusinessException("Araba Zaten Bakımda.");
        }
    }


}
package com.etiya.rentACar.business.concretes;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.DamageDto;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.DamageDao;
import com.etiya.rentACar.entities.Damage;

import java.util.List;
import java.util.stream.Collectors;

//managerda o managerın ait olduğu türe ait dao dışında başka bir dao bulunamaz. Sadece başka bir servis injection edilebilir.
@Service
public class DamageManager implements DamageService {

    private DamageDao damageDao;
    private ModelMapperService modelMapperService;
    private CarService carService;

    public DamageManager(DamageDao damageDao, ModelMapperService modelMapperService, CarService carService) {
        this.damageDao = damageDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public void add(CreateDamageRequest createDamageRequest) {

        Damage damage = this.modelMapperService.forRequest().map(createDamageRequest, Damage.class);
        this.damageDao.save(damage);
    }

    @Override
    public void update(UpdateDamageRequest updateDamageRequest) {
        checkIfCarExists(updateDamageRequest.getCarId());
        Damage damage = this.modelMapperService.forRequest().map(updateDamageRequest, Damage.class);
        this.damageDao.save(damage);
    }

    @Override
    public void delete(DeleteDamageRequest deleteDamageRequest) {
        this.damageDao.deleteById(deleteDamageRequest.getId());

    }

    @Override
    public List<ListDamageDto> getAll() {
        List<Damage> damages = this.damageDao.findAll();
        List<ListDamageDto> response = damages.stream().map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListDamageDto> getAllByCarId(int carId) {
        List<Damage> damages = this.damageDao.getAllByCarId(carId);
        List<ListDamageDto> response = damages.stream()
                .map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class))
                .collect(Collectors.toList());
        return response;
    }


    @Override
    public DamageDto getById(int id) {
        Damage damage = this.damageDao.getById(id);
        DamageDto response = this.modelMapperService.forDto().map(damage, DamageDto.class);
        return response;
    }

    private void checkIfCarExists(int carId) {
        if (this.carService.getById(carId) == null) {
            throw new BusinessException("Böyle bir araba mevcut değil");
        }
    }

    @Override
    public List<ListDamageDto> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Damage> damages = this.damageDao.findAll(pageable).getContent();
        List<ListDamageDto> response = damages.stream()
                .map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class))
                .collect(Collectors.toList());
        return response;
    }


    @Override
    public List<ListDamageDto> getAllSorted(String field, String option) {
        Sort sortingField = Sort.by(Sort.Direction.valueOf(option), field);
        List<Damage> damages = this.damageDao.findAll(sortingField);
        List<ListDamageDto> response = damages
                .stream()
                .map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class))
                .collect(Collectors.toList());
        return response;
    }
}

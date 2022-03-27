package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.DamageDao;
import com.etiya.rentACar.entities.Damage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DamageManager implements DamageService {

    private DamageDao damageDao;
    private ModelMapperService modelMapperService;

    public DamageManager(DamageDao damageDao, ModelMapperService modelMapperService) {
        this.damageDao = damageDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateDamageRequest createDamageRequest) {

        Damage damage = this.modelMapperService.forRequest().map(createDamageRequest, Damage.class);
        this.damageDao.save(damage);
        return new SuccessResult("hasar başarıyla eklendi");
    }

    @Override
    public DataResult<List<ListDamageDto>> getAll() {
        List<Damage> damages = this.damageDao.findAll();
        List<ListDamageDto> response = damages.stream().map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListDamageDto>>(response,"hasar listesi başarıyla getirildi");
    }

    @Override
    public DataResult<List<ListDamageDto>> getByCarId(int id) {
        List<Damage> damages = this.damageDao.findAll();
        List<ListDamageDto> response = damages.stream()
                .map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class))
                .filter(listDamageDto -> listDamageDto.getCarId() == id)
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListDamageDto>>(response,"hasar listesi id'ye göre başarıyla getirildi");
    }

    @Override
    public DataResult<List<ListDamageDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Damage> damages = this.damageDao.findAll(pageable).getContent();
        List<ListDamageDto> response = damages.stream()
                .map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListDamageDto>>(response,"hasar listesi başarıyla sayfalandı");
    }


    @Override
    public DataResult<List<ListDamageDto>> getAllSorted(String field,String option) {
            Sort sortingField = Sort.by(Sort.Direction.valueOf(option), field);
            List<Damage> damages = this.damageDao.findAll(sortingField);
            List<ListDamageDto> response = damages
                    .stream()
                    .map(damage -> this.modelMapperService.forDto().map(damage, ListDamageDto.class))
                    .collect(Collectors.toList());
        return new SuccessDataResult<List<ListDamageDto>>(response,"bakım listesi başarıyla sıralandı");
        }
}

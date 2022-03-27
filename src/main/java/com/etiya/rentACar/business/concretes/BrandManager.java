package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.*;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.dataAccess.abstracts.BrandDao;
import com.etiya.rentACar.entities.Brand;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {
    private BrandDao brandDao;
    private ModelMapperService modelMapperService;

    public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
        super();
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateBrandRequest createBrandRequest) {
        checkIfBrandExists(createBrandRequest.getName());
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        this.brandDao.save(brand);
        return new SuccessResult("marka başarıyla eklendi");
    }

    @Override
    public DataResult<List<ListBrandDto>> getAll() {
        List<Brand> brands = this.brandDao.findAll();
        List<ListBrandDto> response = brands.stream()
                .map(brand -> this.modelMapperService.forDto()
                        .map(brand, ListBrandDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListBrandDto>>(response,"marka listesi başarıyla getirildi");
    }

    public void checkIfBrandExists(String brandName){
        if (brandDao.existsBrandByNameIgnoreCase(brandName)) {
            throw new BusinessException("Bu marka daha önce kaydedilmiş");
        }
    }
}

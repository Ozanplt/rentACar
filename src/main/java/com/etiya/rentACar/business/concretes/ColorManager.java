package com.etiya.rentACar.business.concretes;


import org.springframework.stereotype.Service;
import com.etiya.rentACar.business.abstracts.ColorService;
import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.responses.colorResponses.ListColorDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.ColorDao;
import com.etiya.rentACar.entities.Color;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {
    private ColorDao colorDao;
    private ModelMapperService modelMapperService;

    public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService){
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
    }
    @Override
    public void add(CreateColorRequest createColorRequest) {

        checkIfColorExists(createColorRequest.getName());

        Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
        this.colorDao.save(color);

    }

    @Override
    public List<ListColorDto> getAll() {

        List<Color> colors = this.colorDao.findAll();
        List<ListColorDto>  response = colors.stream()
                .map(color->this.modelMapperService.forDto()
                .map(color, ListColorDto.class))
                .collect(Collectors.toList());
        return response;
    }
    public void checkIfColorExists(String colorName){
        if (colorDao.existsColorByNameIgnoreCase(colorName)) {
            throw new BusinessException("Bu renk daha önce kaydedilmiş");
        }
    }

}


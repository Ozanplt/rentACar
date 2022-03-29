package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.responses.colorResponses.ListColorDto;

import java.util.List;

public interface ColorService {

   List<ListColorDto> getAll();

   void add(CreateColorRequest createColorRequest);


}

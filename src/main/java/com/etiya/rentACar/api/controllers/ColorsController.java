package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.requests.colorRequests.DeleteColorRequest;
import com.etiya.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;
import com.etiya.rentACar.business.abstracts.ColorService;
import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.responses.colorResponses.ListColorDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
    private ColorService colorService;

    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) {
        return this.colorService.add(createColorRequest);
    }
    @GetMapping("/getall")
    public DataResult<List<ListColorDto>> getAll(){
        return this.colorService.getAll();
    }

//    @PutMapping("/update")
//    public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest){
//        return this.colorService.update(updateColorRequest);
//    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest){
        return this.colorService.delete(deleteColorRequest);
    }

}

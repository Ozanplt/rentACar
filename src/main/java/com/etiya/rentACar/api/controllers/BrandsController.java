package com.etiya.rentACar.api.controllers;


import com.etiya.rentACar.business.requests.brandRequests.DeleteBrandRequest;
import com.etiya.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;
import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

    private BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        return this.brandService.add(createBrandRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListBrandDto>> getAll() {
        return this.brandService.getAll();
    }


//    @PutMapping("/update")
//    public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest){
//        return this.brandService.update(updateBrandRequest);
//    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest){
        return this.brandService.delete(deleteBrandRequest);
    }
}


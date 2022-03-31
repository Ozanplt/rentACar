package com.etiya.rentACar.business.requests.carRequests;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.etiya.rentACar.entities.CarStates;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest{

    @NotNull
    private int id;

    @NotNull
    @Min(1)
    @Max(2000)
    private double dailyPrice;

    @NotNull
    @Length(min=2, max=50)
    private String description;

    @NotNull
    @Min(2015)
    private double modelYear;

    @NotNull
    private int colorId;

    @NotNull
    private int brandId;

    @NotNull
    private CarStates carState;

    @NotNull
    @Length(min=2)
    private String city;

}

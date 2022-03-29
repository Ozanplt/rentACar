package com.etiya.rentACar.business.requests.carRequests;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.etiya.rentACar.entities.CarStates;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest extends CreateCarRequest{


    private int carId;

    private double dailyPrice;

    private String description;

    private double modelYear;

    private int colorId;

    private int brandId;

    private CarStates carState;


}

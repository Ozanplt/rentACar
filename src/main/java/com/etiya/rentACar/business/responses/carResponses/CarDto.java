package com.etiya.rentACar.business.responses.carResponses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.etiya.rentACar.entities.CarStates;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    @JsonIgnore
    private int id;
    private double dailyPrice;
    private String description;
    private double modelYear;
    private String colorName;
    private String brandName;
    private CarStates carState;
    @JsonIgnore
    private LocalDate maintenanceReturnDate;
}

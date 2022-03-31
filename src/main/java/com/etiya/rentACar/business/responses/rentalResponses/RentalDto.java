package com.etiya.rentACar.business.responses.rentalResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RentalDto {

    private int id;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private int carId;
    private int customerId;
    private String rentCity;
    private String deliveryCity;
    private double lateFee;
}
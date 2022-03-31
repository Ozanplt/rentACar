package com.etiya.rentACar.business.requests.rentalRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DifferentRentDeliveryCityRequest {

    private String rentCity;
    private String deliveryCity;
    private int carId;
}

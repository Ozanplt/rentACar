package com.etiya.rentACar.business.requests.paymentRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {

    @JsonIgnore
    private int id;

    private LocalDate rentDate;

    private LocalDate returnDate;

    private String cardNumber;

    private String cardFirstName;

    private String cardLastName;

    private String expirationDate;

    private String cvc;

    private String customerFirstName; // deneme

    private String customerLastName;

    private int carId;

    private int returnCityId;

    private int rentCityId;
}

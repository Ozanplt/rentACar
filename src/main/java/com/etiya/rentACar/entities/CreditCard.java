package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {


    private String cardNumber;

    private String cardFirstName;

    private String cardLastName;

    private String expirationDate;

    private String cvc;

}

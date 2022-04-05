package com.etiya.rentACar.business.responses.orderedAdditionalPropertyResponses;

import com.etiya.rentACar.entities.AdditionalProperty;
import com.etiya.rentACar.entities.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderedAdditionalPropertyDto {

    private int id;

    private int rentalId;

    private int additionalPropertyId;
}

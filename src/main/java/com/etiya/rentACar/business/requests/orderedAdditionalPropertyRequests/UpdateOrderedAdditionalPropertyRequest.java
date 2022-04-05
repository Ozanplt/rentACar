package com.etiya.rentACar.business.requests.orderedAdditionalPropertyRequests;

import com.etiya.rentACar.entities.AdditionalProperty;
import com.etiya.rentACar.entities.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderedAdditionalPropertyRequest {
    private int id;

    private int rentalId;

    private int additionalPropertyId;
}

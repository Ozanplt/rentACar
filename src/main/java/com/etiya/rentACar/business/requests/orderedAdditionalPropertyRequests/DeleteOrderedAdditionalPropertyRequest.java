package com.etiya.rentACar.business.requests.orderedAdditionalPropertyRequests;

import com.etiya.rentACar.entities.AdditionalProperty;
import com.etiya.rentACar.entities.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderedAdditionalPropertyRequest {
    private int id;

}

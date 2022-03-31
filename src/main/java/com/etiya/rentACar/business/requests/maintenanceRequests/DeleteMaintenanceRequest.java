package com.etiya.rentACar.business.requests.maintenanceRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMaintenanceRequest {

    @JsonIgnore
    private int id;
    @NotNull
    private int carId;
}

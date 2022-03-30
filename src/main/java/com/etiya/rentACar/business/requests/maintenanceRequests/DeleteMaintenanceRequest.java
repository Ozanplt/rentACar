package com.etiya.rentACar.business.requests.maintenanceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMaintenanceRequest {
    @NotNull
    private int id;
}

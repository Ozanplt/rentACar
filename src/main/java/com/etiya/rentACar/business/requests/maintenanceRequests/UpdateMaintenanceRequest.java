package com.etiya.rentACar.business.requests.maintenanceRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest {

    @JsonIgnore
    private int id;

    @NotNull
    @FutureOrPresent
    private LocalDate returnDate;
    @NotNull
    @FutureOrPresent
    private LocalDate addDate;

    @NotNull
    private int carId;
}

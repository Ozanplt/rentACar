package com.etiya.rentACar.business.requests.damageRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDamageRequest {

    @JsonIgnore
    private int id;

    @NotNull
    private int carId;
    @NotNull
    @Length(min=1,max=50)
    private String description;

    @NotNull
    @PastOrPresent
    private LocalDate date;



}
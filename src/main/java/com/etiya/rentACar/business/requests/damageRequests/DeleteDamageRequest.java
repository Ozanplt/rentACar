package com.etiya.rentACar.business.requests.damageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDamageRequest {
    @NotNull
    private int id;

}

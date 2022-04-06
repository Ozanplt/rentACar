package com.etiya.rentACar.business.requests.invoiceRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
    private int id;

    private LocalDate createDate;

    private int customerId;
    @JsonIgnore
    private int rentalId;

    private LocalDate rentDate;

    private LocalDate returnDate;
    @JsonIgnore
    private double totalPricePayment;

    private String billNo;
    @JsonIgnore
    private int rentalDay;

}
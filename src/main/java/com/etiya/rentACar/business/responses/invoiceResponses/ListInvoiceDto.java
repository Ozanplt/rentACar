package com.etiya.rentACar.business.responses.invoiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInvoiceDto {

    private int id;

    private LocalDate createDate;

    private int rentalId;

    private LocalDate rentDate;

    private LocalDate returnDate;

    private double totalPricePayment;

    private String billNo;

    private String customerFirstName;

    private String customerLastName;
    private int rentalDay;

}

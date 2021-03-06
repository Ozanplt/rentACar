package com.etiya.rentACar.api.controllers;


import com.etiya.rentACar.business.abstracts.InvoiceService;
import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.DeleteInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.etiya.rentACar.business.responses.invoiceResponses.InvoiceDto;
import com.etiya.rentACar.business.responses.invoiceResponses.ListInvoiceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {
    private InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) {
        return this.invoiceService.add(createInvoiceRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListInvoiceDto>> getAll() {
        return this.invoiceService.getAll();
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) {
        return this.invoiceService.update(updateInvoiceRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) {
        return this.invoiceService.delete(deleteInvoiceRequest);
    }

    @GetMapping("/getallcreatedate")
    public DataResult<List<ListInvoiceDto>> getAllCreateDate(@RequestParam("firstcreatedate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)LocalDate firstDate,
                                                             @RequestParam("endcreatedate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)LocalDate endDate){
    return this.invoiceService.getByCreateDateBetween(firstDate,endDate);

    }
    @GetMapping("/getallcustomerid")
    public DataResult<List<ListInvoiceDto>> getAllCustomerId(@RequestParam("customerid") int customerId){
        return this.invoiceService.getByCustomerId(customerId);
    }
    @GetMapping("/getbyid")
    public DataResult<InvoiceDto> getById(@RequestParam("id") int id){
        return this.invoiceService.getById(id);
    }

}

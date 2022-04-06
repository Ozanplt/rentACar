package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.InvoiceService;
import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.DeleteInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.etiya.rentACar.business.responses.invoiceResponses.InvoiceDto;
import com.etiya.rentACar.business.responses.invoiceResponses.ListInvoiceDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.InvoiceDao;
import com.etiya.rentACar.entities.Invoice;
import com.etiya.rentACar.entities.Rental;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class InvoiceManager implements InvoiceService {
    private InvoiceDao invoiceDao;
    private ModelMapperService modelMapperService;

    public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService) {
        this.invoiceDao = invoiceDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListInvoiceDto>> getAll() {
        List<Invoice> invoices = this.invoiceDao.findAll();
        List<ListInvoiceDto> response = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, ListInvoiceDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListInvoiceDto>>(response);
    }

    @Override
    public Result add(CreateInvoiceRequest createInvoiceRequest) {

        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        this.invoiceDao.save(invoice);
        return new SuccessResult("Invoice added");
    }

    @Override
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
        Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
        this.invoiceDao.save(invoice);
        return new SuccessResult("PAYMENT_UPDATE");
    }

    @Override
    public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
        this.invoiceDao.deleteById(deleteInvoiceRequest.getId());
        return new SuccessResult("PAYMENT_DELETED");
    }

    @Override
    public DataResult<List<ListInvoiceDto>> getByCreateDateBetween(LocalDate firstDate, LocalDate endDate) {
        List<Invoice> invoices = this.invoiceDao.getByCreateDateBetween(firstDate,endDate);
        List<ListInvoiceDto> response = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, ListInvoiceDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListInvoiceDto>>(response);
    }

    @Override
    public DataResult<List<ListInvoiceDto>> getByCustomerId(int id) {
        List<Invoice> invoices = this.invoiceDao.getByCustomerId(id);
        List<ListInvoiceDto> response = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, ListInvoiceDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListInvoiceDto>>(response);
    }

    public DataResult<InvoiceDto> getById(int id){
        Invoice invoice = this.invoiceDao.getById(id);
        InvoiceDto invoiceDto = this.modelMapperService.forDto().map(invoice,InvoiceDto.class);
        return new SuccessDataResult<InvoiceDto>(invoiceDto);
    }


    @Override
    public DataResult<InvoiceDto> getByLastInvoice() {
        List<Invoice> invoices = this.invoiceDao.findAll();
        Invoice invoice = invoices.get(invoices.size() - 1);
        InvoiceDto invoiceDto = this.modelMapperService.forDto().map(invoice, InvoiceDto.class);
        return new SuccessDataResult<InvoiceDto>(invoiceDto);
    }
}
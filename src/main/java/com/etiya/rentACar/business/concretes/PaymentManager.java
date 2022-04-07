package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.*;
import com.etiya.rentACar.business.adapters.PaymentBankAdapterService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalPropertyRequests.CreateOrderedAdditionalPropertyRequest;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.ListAdditionalPropertyDto;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.cityResponses.CityDto;
import com.etiya.rentACar.business.responses.orderedAdditionalPropertyResponses.ListOrderedAdditionalPropertyDto;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.PaymentDao;
import com.etiya.rentACar.entities.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentManager implements CityService.PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private CustomerService customerService;
    private RentalService rentalService;
    private CityService cityService;
    private CarService carService;
    private InvoiceService invoiceService;
    private PaymentBankAdapterService paymentBankAdapterService;
    private OrderedAdditionalPropertyService orderedAdditionalPropertyService;
    private AdditionalPropertyService additionalPropertyService;

    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, CustomerService customerService, RentalService rentalService, CityService cityService, CarService carService, InvoiceService invoiceService, PaymentBankAdapterService paymentBankAdapterService, OrderedAdditionalPropertyService orderedAdditionalPropertyService, AdditionalPropertyService additionalPropertyService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.customerService = customerService;
        this.rentalService = rentalService;
        this.cityService = cityService;
        this.carService = carService;
        this.invoiceService = invoiceService;
        this.paymentBankAdapterService = paymentBankAdapterService;
        this.orderedAdditionalPropertyService = orderedAdditionalPropertyService;
        this.additionalPropertyService = additionalPropertyService;
    }

    @Override
    public Result add(CreatePaymentRequest createPaymentRequest) {
        this.makePayment(createPaymentRequest);

        Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

        this.newCustomerForPayment(createPaymentRequest);
        Customer customer = this.modelMapperService.forDto().map(this.customerService.getByLastCustomer().getData(), Customer.class);

        this.newRentalForPayment(createPaymentRequest);
        Rental rental = this.modelMapperService.forDto().map(this.rentalService.getByLastRental().getData(), Rental.class);

        this.newInvoiceForPayment(createPaymentRequest, rental, customer);
        Invoice invoice = this.modelMapperService.forRequest().map(this.invoiceService.getByLastInvoice().getData(), Invoice.class);

        this.newOrderedAdditionalProperty(createPaymentRequest,rental);

        this.newPayment(createPaymentRequest,payment,customer,rental,invoice);

        return new SuccessResult(BusinessMessages.PaymentMessage.PAYMENT_ADDED);
    }

    @Override
    public DataResult<List<ListPaymentDto>> getALl() {

        List<Payment> payments = this.paymentDao.findAll();
        List<ListPaymentDto> response = payments.stream()
                .map(payment -> this.modelMapperService.forDto().map(payment, ListPaymentDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListPaymentDto>>(response);

    }


    public double addTotalPrice(CreatePaymentRequest createPaymentRequest) {

        DataResult<RentalDto> rentalDto = this.rentalService.getById(createPaymentRequest.getCarId());
        int dayDiff = diffDates(createPaymentRequest);
        double carTotalPrice = dayDiff * rentalDto.getData().getTotalPrice();
        double additionalPropertyTotalPrice = dayDiff * additionalPropertyTotal(createPaymentRequest);
        double cityDiff = checkCity(createPaymentRequest);
        return (carTotalPrice + cityDiff + additionalPropertyTotalPrice);

    }


    public int diffDates(CreatePaymentRequest createPaymentRequest) {
        long period = ChronoUnit.DAYS.between(createPaymentRequest.getRentDate(), createPaymentRequest.getReturnDate());
        return (int) period;
    }

    public double checkCity(CreatePaymentRequest createPaymentRequest) {

        if (createPaymentRequest.getRentCityId() != createPaymentRequest.getReturnCityId()) {

            return 750;
        }
        return 0;
    }

    public double updateKilometer(int carId) {
        CarDto carDto = this.carService.getCarKilometer(carId);
        return carDto.getCarKilometer();

    }

    public void newCustomerForPayment(CreatePaymentRequest createPaymentRequest) {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setFirstName(createPaymentRequest.getCustomerFirstName());
        createCustomerRequest.setLastName(createPaymentRequest.getCustomerLastName());
        this.customerService.add(createCustomerRequest);
    }

    public void newRentalForPayment(CreatePaymentRequest createPaymentRequest) {
        CreateRentalRequest createRentalRequest = new CreateRentalRequest();
        createRentalRequest.setReturnDate(createPaymentRequest.getReturnDate());
        createRentalRequest.setRentDate(createPaymentRequest.getRentDate());
        createRentalRequest.setCustomerId(this.customerService.getByLastCustomer().getData().getId());
        createRentalRequest.setCarId(createPaymentRequest.getCarId());
        createRentalRequest.setStartKilometer(updateKilometer(createPaymentRequest.getCarId()));
        createRentalRequest.setRentCityId(createPaymentRequest.getRentCityId());
        createRentalRequest.setReturnCityId(createPaymentRequest.getReturnCityId());
        createRentalRequest.setTotalPrice(this.rentalService.setDiscountedPrice(createPaymentRequest.getCarId()));
        this.rentalService.add(createRentalRequest);
    }

    public void newInvoiceForPayment(CreatePaymentRequest createPaymentRequest, Rental rental, Customer customer) {
        CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest.setBillNo("123456ABC"); //random metodu ile
        createInvoiceRequest.setRentalId(rental.getId());
        createInvoiceRequest.setCreateDate(LocalDate.now());

        //*****Error****
//        createInvoiceRequest.setReturnDate(createPaymentRequest.getReturnDate());
        //********


        createInvoiceRequest.setRentDate(createPaymentRequest.getRentDate());
        createInvoiceRequest.setCustomerId(customer.getId());
        createInvoiceRequest.setTotalPricePayment(addTotalPrice(createPaymentRequest));
        createInvoiceRequest.setRentalDay(diffDates(createPaymentRequest));
        this.invoiceService.add(createInvoiceRequest);

    }

    public City getCity(CreatePaymentRequest createPaymentRequest) {
        CityDto cityDto = this.cityService.getById(createPaymentRequest.getRentCityId()).getData();
        City city = this.modelMapperService.forDto().map(cityDto, City.class);
        return city;
    }

    public void makePayment(CreatePaymentRequest createPaymentRequest) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(createPaymentRequest.getCardNumber());
        creditCard.setCardFirstName(createPaymentRequest.getCardFirstName());
        creditCard.setCardLastName(createPaymentRequest.getCardLastName());
        creditCard.setCvc(createPaymentRequest.getCvc());
        creditCard.setExpirationDate(createPaymentRequest.getExpirationDate());
        if (this.paymentBankAdapterService.pay(creditCard)) {
        } else {
            throw new BusinessException(BusinessMessages.PaymentMessage.PAYMENT_NOT_ACCEPTED);
        }
    }


    public double additionalPropertyTotal(CreatePaymentRequest createPaymentRequest) {
        double totalPrice = 0;
        for (int orderedAdditionalItem : createPaymentRequest.getOrderedAdditionalPropertyIdentities()) {

            List<ListOrderedAdditionalPropertyDto> result2 = this.orderedAdditionalPropertyService.getAllIdById(orderedAdditionalItem);

            for (int additionalItem : result2.stream().map(item -> item.getAdditionalPropertyId()).collect(Collectors.toList())) {
                ListAdditionalPropertyDto response = this.additionalPropertyService.getById(additionalItem);
                totalPrice += response.getDailyPrice();
            }
        }
        return totalPrice;
    }

    public void newOrderedAdditionalProperty(CreatePaymentRequest createPaymentRequest, Rental rental){

        for (int orderedAdditionalItem : createPaymentRequest.getOrderedAdditionalPropertyIdentities()) {
            CreateOrderedAdditionalPropertyRequest createOrderedAdditionalPropertyRequest = new CreateOrderedAdditionalPropertyRequest();
            createOrderedAdditionalPropertyRequest.setRentalId(rental.getId());
            createOrderedAdditionalPropertyRequest.setAdditionalPropertyId(orderedAdditionalItem);
            orderedAdditionalPropertyService.add(createOrderedAdditionalPropertyRequest);

        }

    }

    public void newPayment(CreatePaymentRequest createPaymentRequest, Payment payment, Customer customer, Rental rental,Invoice invoice){
        payment.setDayCount(diffDates(createPaymentRequest));
        payment.setCustomer(customer);
        payment.setRentCity(this.getCity(createPaymentRequest));
        payment.setTotalPrice(addTotalPrice(createPaymentRequest));
        payment.setDayCount(diffDates(createPaymentRequest));
        payment.setRental(rental);
        payment.setInvoice(invoice);
        this.paymentDao.save(payment);
    }
}

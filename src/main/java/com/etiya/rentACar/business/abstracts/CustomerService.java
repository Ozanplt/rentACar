package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.rentACar.business.requests.customerRequests.DeleteCustomerRequest;
import com.etiya.rentACar.business.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.rentACar.business.responses.customerResponses.CustomerDto;
import com.etiya.rentACar.business.responses.customerResponses.ListCustomerDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

public interface CustomerService {

    DataResult<List<ListCustomerDto>> getAll();

    Result delete(DeleteCustomerRequest deleteCustomerRequest);
    Result update(UpdateCustomerRequest updateCustomerRequest);
    Result add(CreateCustomerRequest createCustomerRequest);
    DataResult<CustomerDto> getByLastCustomer();
}
package com.etiya.rentACar.business.adapters;

import com.etiya.rentACar.entities.CreditCard;

public interface PaymentBankAdapterService {
    public boolean pay(CreditCard creditCard);
}

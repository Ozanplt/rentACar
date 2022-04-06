package com.etiya.rentACar.business.adapters;

import com.etiya.rentACar.entities.CreditCard;
import com.etiya.rentACar.payService.IsBankPosManager;
import org.springframework.stereotype.Service;

@Service
public class PaymentIsBankAdapterManager implements PaymentBankAdapterService {


    private IsBankPosManager isBankPosManager;


    public PaymentIsBankAdapterManager(IsBankPosManager isBankPosManager ) {
        this.isBankPosManager = isBankPosManager;

    }


    @Override
    public boolean pay(CreditCard creditCard) {
        return isBankPosManager.pay(creditCard.getCardNumber(), creditCard.getCardFirstName(), creditCard.getCardLastName(), creditCard.getCvc(), creditCard.getExpirationDate());
    }

}

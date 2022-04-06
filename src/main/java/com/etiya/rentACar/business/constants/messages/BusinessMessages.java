package com.etiya.rentACar.business.constants.messages;

public class BusinessMessages {
    public class MaintenanceMessages{

        public static final String MAINTENANCE_DATE_NOT_AVAILABLE="tarihler uygun değil";
        public static final String MAINTENANCE_ADDED="BAKIM EKLENDİ";
        public static final String MAINTENANCE_UPDATED="BAKIM GÜNCELLENDİ";
        public static final String MAINTENANCE_DELETED="BAKIM SİLİNDİ";
    }
    public class CarStateMessage {
        public static final String CAR_STATE_UNDER_MAINTENANCE="Bu araç bakımda";
        public static final String CAR_STATE_AVAILABLE="araç uygundur";
        public static final String CAR_STATE_RENTED="bu araç kirada";
        public static final String CAR_NOT_AVAILABLE = "Araba uygun değil!";


    }
    public class BrandMessage {
        public static final String BRAND_NAME_EXISTS = "Girdiğiniz marka mevcuttur";
        public static final String BRAND_ADDED="MARKA EKLENDİ";
        public static final String BRAND_UPDATED="MARKA GÜNCELLENDİ";
        public static final String BRAND_DELETED="MARKA SİLİNDİ";

    }
    public class ColorMessage {
        public static final String COLOR_NAME_EXISTS = "Girdiğiniz renk mevcuttur!";
        public static final String COLOR_ADDED="RENK EKLENDİ";
        public static final String COLOR_UPDATED="RENK GÜNCELLENDİ";
        public static final String COLOR_DELETED="RENK SİLİNDİ";

    }
    public class CarMessage {
        public static final String PRICE_MUST_UPPER_THAN_50 = " !";
        public static final String CAR_ADDED="ARABA EKLENDİ";
        public static final String CAR_UPDATED="ARABA GÜNCELLENDİ";
        public static final String CAR_DELETED="ARABA SİLİNDİ";
        public static final String CAR_NOT_EXISTS = "Böyle bir araba mevcut değil!";

    }

    public class RentalMessage {
        public static final String RENTAL = " !";
        public static final String RENTAL_ADDED="KİRALAMA EKLENDİ";
        public static final String RENTAL_UPDATED="KİRALAMA GÜNCELLENDİ";
        public static final String RENTAL_DELETED="KİRALAMA SİLİNDİ";

    }
    public class CarDamageMessage {

        public static final String DAMAGE_ADDED="HASAR EKLENDİ";
        public static final String DAMAGE_UPDATED="HASAR GÜNCELLENDİ";
        public static final String DAMAGE_DELETED="HASAR SİLİNDİ";

    }

    public class CustomerMessage {
        public static final String CUSTOMER = " !";
        public static final String CUSTOMER_ADDED="MÜŞTERİ EKLENDİ";
        public static final String CUSTOMER_UPDATED="MÜŞTERİ GÜNCELLENDİ";
        public static final String CUSTOMER_DELETED="MÜŞTERİ SİLİNDİ";

    }

    public class AdditionalPropertyMessage {
        public static final String ADDITIONAL_PROPERTY = " !";
        public static final String ADDITIONAL_PROPERTY_ADDED="EK ÖZELLİK EKLENDİ";
        public static final String ADDITIONAL_PROPERTY_UPDATED="EK ÖZELLİK GÜNCELLENDİ";
        public static final String ADDITIONAL_PROPERTY_DELETED="EK ÖZELLİK SİLİNDİ";
        public static final String ADDITIONAL_PROPERTY_ALREADY_EXISTS="BU ÖZELLİK ZATEN VAR";

    }
    public class CityMessage {
        public static final String CITY = " !";
        public static final String CITY_ADDED="ŞEHİR EKLENDİ";
        public static final String CITY_UPDATED="ŞEHİR GÜNCELLENDİ";
        public static final String CITY_DELETED="ŞEHİR SİLİNDİ";
        public static final String CITY_ALREADY_EXISTS="ŞEHİR ZATEN VAR";

    }

    public class OrderedAdditionalPropertyMessage {
        public static final String ORDERED_ADDITIONAL_PROPERTY = " !";
        public static final String ORDERED_ADDITIONAL_PROPERTY_ADDED="EK ÖZELLİK HESABA EKLENDİ";
        public static final String ORDERED_ADDITIONAL_PROPERTY_UPDATED="EK ÖZELLİK HESABA GÜNCELLENDİ";
        public static final String ORDERED_ADDITIONAL_PROPERTY_DELETED="EK ÖZELLİK HESABA SİLİNDİ";
//      public static final String ORDERED_ADDITIONAL_PROPERTY_ALREADY_EXISTS="BU ÖZELLİK HESAPTA ZATEN VAR";

    }

    public class InvoiceMessage {
        public static final String INVOICE = " !";
        public static final String INVOICE_ADDED="FATURA EKLENDİ";
        public static final String INVOICE_UPDATED="FATURA GÜNCELLENDİ";
        public static final String INVOICE_DELETED="FATURA SİLİNDİ";

    }

    public class PaymentMessage {
        public static final String PAYMENT = " !";
        public static final String PAYMENT_ADDED="ÖDEME EKLENDİ";
        public static final String PAYMENT_UPDATED="ÖDEME GÜNCELLENDİ";
        public static final String PAYMENT_DELETED="ÖDEME SİLİNDİ";
        public static final String PAYMENT_NOT_ACCEPTED ="ÖDEME KABUL EDİLMEDİ";

    }
}

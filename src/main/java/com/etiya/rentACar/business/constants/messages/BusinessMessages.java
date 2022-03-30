package com.etiya.rentACar.business.constants.messages;

public class BusinessMessages {
    public class MaintenanceMessages{
        public static final String CAR_UNDER_MAINTENANCE="Bu araç bakımda";
        public static final String CAR_RENTED="bu araç kirada";
        public static final String CAR_AVAILABLE="araç uygundur";
        public static final String DATE_NOT_AVAILABLE="tarihler uygun değil";
    }
    public class CarStateMessage {
        public static final String CAR_NOT_AVAILABLE = "Araba uygun değil!";

    }
    public class BrandMessage {
        public static final String BRAND_NAME_EXISTS = "Girdiğiniz marka mevcuttur";

    }
    public class ColorMessage {
        public static final String COLOR_NAME_EXISTS = "Girdiğiniz renk mevcuttur!";

    }
    public class CarMessage {
        public static final String PRICE_MUST_UPPER_THAN_50 = " !";

    }

    public class RentalMessage {
        public static final String RENTAL = " !";

    }
    public class CarDamageMessage {
        public static final String CAR_NOT_EXISTS = "Böyle bir araba mevcut değil!";

    }

    public class CustomerMessage {
        public static final String CUSTOMER = " !";

    }
}

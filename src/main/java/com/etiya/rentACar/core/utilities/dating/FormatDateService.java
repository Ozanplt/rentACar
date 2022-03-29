package com.etiya.rentACar.core.utilities.dating;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public interface FormatDateService {
    Date getFormattedDate(LocalDate date) throws ParseException;
}

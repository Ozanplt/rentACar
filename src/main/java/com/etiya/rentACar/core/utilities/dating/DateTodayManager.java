package com.etiya.rentACar.core.utilities.dating;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class DateTodayManager implements DateTodayService{
    
    public Date getTodayTime() throws ParseException {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String formattedDate = myDateObj.format(myFormatObj);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date newDate = formatter.parse(formattedDate);

        return newDate;
    }
}

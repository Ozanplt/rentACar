package com.etiya.rentACar.core.utilities.dating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FormatDate implements FormatDateService{

    public Date getFormattedDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date newDate = formatter.parse(date);
        return newDate;
    }

}

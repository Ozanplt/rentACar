package com.etiya.rentACar.core.utilities.dating;

import java.text.ParseException;
import java.util.Date;

public interface DateTodayService {
    Date getTodayTime() throws ParseException;
}

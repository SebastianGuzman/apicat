package com.cbastian.apicat.resources.usecase.service.time;

import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@NoArgsConstructor
public class TimeManagerService {
    private static final String ISO_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE = "UTC";

    public String getInstantIsoFormat() {
        TimeZone timeZone = TimeZone.getTimeZone(TIME_ZONE);
        DateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(new Date());
    }


}

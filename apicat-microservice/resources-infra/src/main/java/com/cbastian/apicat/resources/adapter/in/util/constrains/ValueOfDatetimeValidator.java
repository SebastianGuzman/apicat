package com.cbastian.apicat.resources.adapter.in.util.constrains;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Slf4j
public class ValueOfDatetimeValidator implements ConstraintValidator<ValueOfDatetime, String> {
    private static final String ISO_FORMAT="yyyy-MM-ddTHH:mm:ss";
    @Override
    public boolean isValid(String datetime, ConstraintValidatorContext constraintValidatorContext) {
        boolean response=false;
        TimeZone timeZone=TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
        dateFormat.setTimeZone(timeZone);

        try{
           // String tempDate= dateFormat.parse(datetime).toString();
            response=true;
        }
        catch (Exception exception){
            log.warn("Datetime format error:{}",datetime);
        }

        return response;

    }
}

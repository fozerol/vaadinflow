/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.converter;


import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    
        @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
    	return (localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
    	return (date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    /*
    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
        Instant instant = Instant.from(date);
        return Date.from(instant);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value) {
        Instant instant = value.toInstant();
        return LocalDate.from(instant);
    }*/
}
}
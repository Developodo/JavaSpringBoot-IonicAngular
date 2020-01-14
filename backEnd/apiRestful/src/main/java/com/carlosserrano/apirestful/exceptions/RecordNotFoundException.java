package com.carlosserrano.apirestful.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * We have annotated our above exception class with @ResponseStatus. 
 * This annotation tells Spring Boot to respond with the specified HTTP status 
 * code in case the exception is thrown.
 * In Our example, we have thrown HttpStatus.NOT_FOUND.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
	
    private String exceptionDetail;
    private Object fieldValue;

    public RecordNotFoundException( String exceptionDetail, Long fieldValue) {
        super(exceptionDetail+" - "+fieldValue);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = fieldValue;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}

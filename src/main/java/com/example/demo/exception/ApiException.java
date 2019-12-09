package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {

    private final String MESSAGE;
//    private final Throwable THROWABLE;
    private final HttpStatus HTTP_STATUS;
    private final ZonedDateTime TIME_STAMP;

    public ApiException(String message, Throwable throwable, HttpStatus http_status, ZonedDateTime time_stamp) {
        MESSAGE = message;
//        THROWABLE = throwable;
        HTTP_STATUS = http_status;
        TIME_STAMP = time_stamp;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

//    public Throwable getTHROWABLE() {
//        return THROWABLE;
//    }

    public HttpStatus getHTTP_STATUS() {
        return HTTP_STATUS;
    }

    public ZonedDateTime getTIME_STAMP() {
        return TIME_STAMP;
    }
}

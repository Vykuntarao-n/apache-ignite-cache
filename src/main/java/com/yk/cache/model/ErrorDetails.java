package com.yk.cache.model;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}

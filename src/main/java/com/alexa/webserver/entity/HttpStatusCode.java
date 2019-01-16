package com.alexa.webserver.entity;

public enum HttpStatusCode {
    OK ("200 OK"),
    NOT_FOUND("404 Not Found"),
    BAD_REQUEST("400 Bad Request"),
    INTERNAL_ERROR ("500 Internal Error"),
    METHOD_NOT_ALLOWED ("405 Method Not Allowed");

    private String status;

    HttpStatusCode (String status){this.status = status;}

    public String getStatus() {
        return status;
    }
}

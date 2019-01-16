package com.alexa.webserver.exception;

import com.alexa.webserver.entity.HttpStatusCode;

public class WebServerException extends RuntimeException {
    private HttpStatusCode httpStatusCode;

    public WebServerException(String message, Throwable cause, HttpStatusCode httpStatusCode) {
        super(message, cause);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }
}

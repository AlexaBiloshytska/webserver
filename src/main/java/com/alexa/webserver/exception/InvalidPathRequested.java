package com.alexa.webserver.exception;

public class InvalidPathRequested extends RuntimeException {

    public InvalidPathRequested() {
    }

    public InvalidPathRequested(String message) {
        super(message);
    }
}

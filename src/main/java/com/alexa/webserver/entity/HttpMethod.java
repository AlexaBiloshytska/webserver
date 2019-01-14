package com.alexa.webserver.entity;

public enum HttpMethod {
    GET ("GET"),
    POST ("POST");

    private String name;

    HttpMethod(String name) {
        this.name = name;
    }

    static HttpMethod getHttpMethodByName (String name) {
        for ( HttpMethod httpMethod: values()) {
            if (httpMethod.name.equalsIgnoreCase(name)){
                return httpMethod;
            }
        }
        throw new IllegalArgumentException("No HttpMethod with name " + name + "found");
    }
}

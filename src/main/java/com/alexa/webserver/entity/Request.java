package com.alexa.webserver.entity;

import java.util.Map;

public class Request {
    private String url;
    private Map<String, String> headers;
    private HttpMethod method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", headers=" + headers +
                ", method=" + method +
                '}';
    }
}

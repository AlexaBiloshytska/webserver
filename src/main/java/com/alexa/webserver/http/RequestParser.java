package com.alexa.webserver.http;

import com.alexa.webserver.entity.HttpMethod;
import com.alexa.webserver.entity.HttpStatusCode;
import com.alexa.webserver.entity.Request;
import com.alexa.webserver.exception.WebServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public Request parserRequest(BufferedReader reader)  {
        Request request = new Request();
        injectUrlAndMethod(request, reader);
        injectHeaders(request,reader);
        return request;
     }


    private void injectUrlAndMethod (Request request, BufferedReader reader) {
        try {
            String requestLine = reader.readLine();
            String[] split = requestLine.split(" ");
            request.setUrl(split[1]);
            request.setMethod(HttpMethod.valueOf(split[0]));
        } catch (IOException e) {
            throw new WebServerException("Failed to read request line", e, HttpStatusCode.BAD_REQUEST);
        }
    }

    private void injectHeaders(Request request, BufferedReader reader){
        String requestLine;
        Map headers = new HashMap();
        try {
            while ((requestLine = reader.readLine()) != null && !requestLine.isEmpty()) {
                String[] split = requestLine.split(": ");
                headers.put(split[0], split[1]);
            }
            request.setHeaders(headers);
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to parse headers from request");
            e.printStackTrace();
        }
    }

}

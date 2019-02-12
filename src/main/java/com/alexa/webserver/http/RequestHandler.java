package com.alexa.webserver.http;

import com.alexa.webserver.entity.HttpMethod;
import com.alexa.webserver.entity.HttpStatusCode;
import com.alexa.webserver.entity.Request;
import com.alexa.webserver.exception.WebServerException;
import com.alexa.webserver.io.ResourceReader;

import java.io.*;

public class RequestHandler implements Runnable{
    private BufferedReader socketReader;
    private BufferedOutputStream socketOS;
    private RequestParser requestParser = new RequestParser();
    private ResourceReader resourceReader = new ResourceReader();

    public void handle () throws IOException {
        try (ResponseWriter responseWriter = new ResponseWriter(socketOS)) {
            try {
                Request request = requestParser.parserRequest(socketReader);
                System.out.println(Thread.currentThread().getName() + " [INFO] Request is received : " + request);

                if (!request.getMethod().equals(HttpMethod.GET)) {
                    throw new WebServerException("Method is nt allowed", null, HttpStatusCode.METHOD_NOT_ALLOWED);
                }

                responseWriter.writeStatusLine(HttpStatusCode.OK);
                responseWriter.setContent(resourceReader.readByteContent(request.getUrl()));
                responseWriter.writeContent();
            } catch (WebServerException e) {
                System.out.println(Thread.currentThread().getName() + " [ERROR] " + e.getMessage());
                responseWriter.writeStatusLine(e.getHttpStatusCode());
                throw new RuntimeException(e.getCause());
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " [ERROR] Exception during processing request");
                responseWriter.writeStatusLine(HttpStatusCode.INTERNAL_ERROR);
            }
        }
    }

    public void setSocketReader(BufferedReader socketIS) {
        this.socketReader = socketIS;
    }

    public void setSocketOS(BufferedOutputStream socketOS) {
        this.socketOS = socketOS;
    }

    @Override
    public void run() {
        try {
            handle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

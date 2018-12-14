package com.alexa.webserver.http;

import com.alexa.webserver.entity.Request;
import com.alexa.webserver.io.ResourceReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class RequestHandler {
    private BufferedReader reader;
    private BufferedWriter writer;
    private ResourceReader resourceReader;
    private RequestParser requestParser = new RequestParser(); // Problem with object creation

    public void handle () {
        //1. Parse request
        Request request =  requestParser.parserRequest(reader);
        String reader = request.getUrl();

        //2.Read content and transfer it to string
        ResourceReader resourceReader = new ResourceReader();
        String content = resourceReader.readContent(reader);

        //3. Transfer data to the writer
        ResponseWriter responseWriter = new ResponseWriter();
        responseWriter.setWriter(writer);
        try {
            responseWriter.writeSuccessResponse(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }
}

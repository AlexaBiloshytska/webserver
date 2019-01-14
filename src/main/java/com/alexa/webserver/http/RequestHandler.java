package com.alexa.webserver.http;

import com.alexa.webserver.entity.HttpStatusCode;
import com.alexa.webserver.entity.Request;
import com.alexa.webserver.exception.InvalidPathRequested;
import com.alexa.webserver.io.ResourceReader;

import java.io.*;

public class RequestHandler {
    private InputStream socketIS;
    private BufferedOutputStream socketOS;
    private RequestParser requestParser = new RequestParser();
    private ResourceReader resourceReader = new ResourceReader();
    private ResponseWriter responseWriter = new ResponseWriter();

    public void handle () throws IOException {
        responseWriter.setWriter(socketOS);
        try {
            //1. Parse request
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socketIS));
            Request request = requestParser.parserRequest(socketReader);
            String url = request.getUrl();

            //2.Read content and transfer it to string
            BufferedInputStream content = resourceReader.readByteContent(url);

            //3. Transfer data to the socketOS

            responseWriter.writeStatusLine(HttpStatusCode.OK);
            responseWriter.writeContent(content);
            responseWriter.finishResponse();
        } catch (InvalidPathRequested invalidPath) {
            System.out.println("[ERROR] Path not found: " + invalidPath.getMessage());
            responseWriter.writeStatusLine(HttpStatusCode.NOT_FOUND);
            responseWriter.finishResponse();
        } catch (Exception e) {
            System.out.println("[ERROR] Exception during processing request");
            responseWriter.writeStatusLine(HttpStatusCode.INTERNAL_ERROR);
            responseWriter.finishResponse();
        }
    }

    public void setSocketIS(InputStream socketIS) {
        this.socketIS = socketIS;
    }

    public void setSocketOS(BufferedOutputStream socketOS) {
        this.socketOS = socketOS;
    }
}

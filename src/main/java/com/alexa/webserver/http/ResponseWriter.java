package com.alexa.webserver.http;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ResponseWriter {
    private BufferedWriter writer;

    public void writeSuccessResponse(String content) throws IOException {

        System.out.println("\n[INFO] Generating response");
        String statusCode = "200 OK";
        writeStatusLine(statusCode);

        writer.write(content);
        writer.flush();
        writer.close();
    }

    public void writeNotFoundResponse () throws IOException {
        String statusCode = "404 NOT FOUND";
        writeStatusLine(statusCode);

        writer.flush();
        writer.close();
    }

    public void writeStatusLine(String statusCode) throws IOException {
        writer.write("HTTP/1.1 " + statusCode);
        writer.newLine();
        writer.newLine();
    }

    public void writeHeaders(){
    }


    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }
}

package com.alexa.webserver.http;

import com.alexa.webserver.entity.HttpStatusCode;

import java.io.*;

public class ResponseWriter {
    private BufferedOutputStream writer;
    private static final byte[] BUFFER = new byte[8142];

    public void writeStatusLine(HttpStatusCode statusCode) throws IOException {
        String statusLine = "HTTP/1.1 " + statusCode.getStatus();

        System.out.println("[INFO] Sending status line : " + statusLine);

        writer.write(statusLine.getBytes());
        writer.write("\n".getBytes());
        writer.write("\n".getBytes());
    }

    public void writeContent(BufferedInputStream content) {
        // TODO : add logs
        try {
            int length;
            while ((length = content.read(BUFFER)) != -1) {
                writer.write(BUFFER, 0, length);
            }
        } catch(IOException e){
            System.out.println("[ERROR]"); // TODO : where description
        } finally {
            // TODO : close content stream
        }
    }

    public void finishResponse() throws IOException{
        writer.flush();
        writer.close();
    }

    public void setWriter(BufferedOutputStream writer) {
        this.writer = writer;
    }
}
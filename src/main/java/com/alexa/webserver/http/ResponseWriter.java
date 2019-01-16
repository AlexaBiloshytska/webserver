package com.alexa.webserver.http;

import com.alexa.webserver.entity.HttpStatusCode;
import com.alexa.webserver.exception.WebServerException;

import java.io.*;

public class ResponseWriter implements Closeable {
    private BufferedOutputStream writer;
    private BufferedInputStream content;
    private static final byte[] BUFFER = new byte[8142];

    public ResponseWriter(BufferedOutputStream socketOS) {
        this.writer = socketOS;
    }

    public void writeStatusLine(HttpStatusCode statusCode) throws IOException {
        String statusLine = "HTTP/1.1 " + statusCode.getStatus();

        System.out.println("[INFO] Sending status line : " + statusLine);

        writer.write(statusLine.getBytes());
        writer.write("\n".getBytes());
        writer.write("\n".getBytes());
    }

    public void writeContent()throws IOException {
        System.out.println("[INFO] Sending content data");
        try {
            int length;
            while ((length = content.read(BUFFER)) != -1) {
                writer.write(BUFFER, 0, length);
            }
        } catch(IOException e){
            throw new WebServerException("Unable to transmit content", e, HttpStatusCode.INTERNAL_ERROR);
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
        content.close();
        System.out.println("[INFO] Resources are released");
    }

    public void setContent(BufferedInputStream content) {
        this.content = content;
    }
}
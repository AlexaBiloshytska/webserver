package com.alexa.webserver;

import com.alexa.webserver.http.RequestHandler;
import com.alexa.webserver.io.ResourceReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private String resourcePath;

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("ServerSocked initiated on port: " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Socket is connected");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setReader(bufferedReader);
            requestHandler.setWriter(bufferedWriter);

            requestHandler.handle();

        }

    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }
}



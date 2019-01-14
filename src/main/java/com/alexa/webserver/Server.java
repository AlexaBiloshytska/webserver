package com.alexa.webserver;

import com.alexa.webserver.http.RequestHandler;

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

            InputStream socketIS = socket.getInputStream();
            BufferedOutputStream socketOS = new BufferedOutputStream(socket.getOutputStream());

            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setSocketIS(socketIS);
            requestHandler.setSocketOS(socketOS);
            try {
                requestHandler.handle();
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to handle request.");
                e.printStackTrace();
            }

        }

    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }
}



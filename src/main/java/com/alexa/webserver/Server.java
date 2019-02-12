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
        System.out.println( Thread.currentThread().getName() + " ServerSocked initiated on port: " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println( Thread.currentThread().getName() + " Socket is connected");

            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedOutputStream socketOS = new BufferedOutputStream(socket.getOutputStream());

            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setSocketReader(socketReader);
            requestHandler.setSocketOS(socketOS);
            try {
                Thread thread = new Thread(requestHandler);
                thread.start();
                System.out.println(thread.getName() + " thread started to handle requests");
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " [ERROR] Unable to handle request.");
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



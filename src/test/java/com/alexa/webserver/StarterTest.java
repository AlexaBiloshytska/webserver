package com.alexa.webserver;

import org.junit.Test;

import java.io.IOException;

public class StarterTest {
    @Test
    public void startServer() throws IOException {
        Server server = new Server();
        server.setPort(3000);
        server.start();
    }
}

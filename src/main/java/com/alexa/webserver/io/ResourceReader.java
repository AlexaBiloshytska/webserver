package com.alexa.webserver.io;


import com.alexa.webserver.Server;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceReader {
    private static final String webAppPath = "/webapp";
    private String resourcePath;

    public String readContent(String path){
        InputStream resourceAsStream = Server.class.getResourceAsStream(webAppPath + path);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));

        StringBuilder stringBuilder = new StringBuilder();
        String value;
        try {
            while ((value= bufferedReader.readLine())!=null) {
                stringBuilder.append(value);
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to read content");
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}

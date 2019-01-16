package com.alexa.webserver.io;

import com.alexa.webserver.entity.HttpStatusCode;
import com.alexa.webserver.exception.WebServerException;

import javax.annotation.Resource;
import java.io.*;

public class ResourceReader {
    private static final String webAppPath = "/webapp";

    public BufferedInputStream readByteContent(String path) {
        InputStream resourceAsStream = getClass().getResourceAsStream(webAppPath + path);
        if (resourceAsStream == null) {
            throw new WebServerException("Path not found: " + path, null, HttpStatusCode.NOT_FOUND);
        }
        return new BufferedInputStream(resourceAsStream);
    }
}

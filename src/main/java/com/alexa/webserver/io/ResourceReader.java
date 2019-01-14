package com.alexa.webserver.io;


import com.alexa.webserver.Server;
import com.alexa.webserver.exception.InvalidPathRequested;

import javax.annotation.Resource;
import java.io.*;

public class ResourceReader {
    private static final String webAppPath = "/webapp";

    public BufferedInputStream readByteContent(String path) {
        InputStream resourceAsStream = getClass().getResourceAsStream(webAppPath + path);
        if (resourceAsStream == null) {
            throw new InvalidPathRequested(path);
        }
        return new BufferedInputStream(resourceAsStream);
    }
}

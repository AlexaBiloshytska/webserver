package com.alexa.webserver;

import com.alexa.webserver.entity.HttpMethod;
import org.junit.Test;

public class HttpMethodTest {
    @Test
    public void testGetEnum() {
        String methodName ="GET";

        HttpMethod httpMethod = HttpMethod.valueOf(methodName);
        System.out.println(methodName);
    }
}

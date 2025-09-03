package com.edgarberlinck.app.util;

import java.io.OutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpResponseBuilder {
    private int statusCode = 200;
    private String statusMessage = "OK";
    private String contentType = "text/plain";
    private byte[] body = new byte[0];

    public HttpResponseBuilder status(int code, String message) {
        this.statusCode = code;
        this.statusMessage = message;
        return this;
    }

    public HttpResponseBuilder contentType(String mime) {
        this.contentType = mime;
        return this;
    }

    public HttpResponseBuilder body(String text) {
        this.body = text.getBytes(StandardCharsets.UTF_8);
        return this;
    }

    public HttpResponseBuilder body(byte[] bytes) {
        this.body = bytes;
        return this;
    }

    public void write(OutputStream out) throws IOException {
        String headers = "HTTP/1.1 " + statusCode + " " + statusMessage + "\r\n" +
                         "Content-Type: " + contentType + "; charset=UTF-8\r\n" +
                         "Content-Length: " + body.length + "\r\n" +
                         "\r\n";

        out.write(headers.getBytes(StandardCharsets.UTF_8));
        out.write(body);
        out.flush();
    }
}


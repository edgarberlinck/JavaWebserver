package com.edgarberlinck.app.util;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestInfo {
  private String fileName;
  private String extension;
  private String mimeType;

  public HttpRequestInfo(String rawRequest) {
    parse(rawRequest);
  }

  private void parse(String raw) {
    // A primeira linha do HTTP request é tipo:
    // GET /index.html HTTP/1.1
    String[] lines = raw.split("\r\n");
    if (lines.length == 0)
      return;

    String[] parts = lines[0].split(" ");
    if (parts.length < 2)
      return;

    String path = parts[1]; // ex: /index.html
    if (path.equals("/")) {
      path = "/index.html"; // fallback básico
    }

    // extrai nome e extensão
    this.fileName = path.substring(path.lastIndexOf("/") + 1);
    int dot = fileName.lastIndexOf(".");
    this.extension = (dot != -1) ? fileName.substring(dot + 1) : "";

    // mapeia mime type tosco
    this.mimeType = guessMimeType(extension);
  }

  private String guessMimeType(String ext) {
    Map<String, String> mime = new HashMap<>();
    mime.put("html", "text/html");
    mime.put("htm", "text/html");
    mime.put("css", "text/css");
    mime.put("js", "application/javascript");
    mime.put("png", "image/png");
    mime.put("jpg", "image/jpeg");
    mime.put("jpeg", "image/jpeg");
    mime.put("gif", "image/gif");
    mime.put("txt", "text/plain");

    return mime.getOrDefault(ext.toLowerCase(), "application/octet-stream");
  }

  public String getFileName() {
    return fileName;
  }

  public String getExtension() {
    return extension;
  }

  public String getMimeType() {
    return mimeType;
  }
}

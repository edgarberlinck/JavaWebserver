package com.edgarberlinck.app.config;

public class AppConfiguration {
  private static AppConfiguration instance;

  private int port = 8067;
  private String httpFolder = "/www";

  public static AppConfiguration getInstance(String[] configs) {
    if (instance == null) {
      instance = new AppConfiguration();
    }

    for (String arg : configs) {
      if (arg.startsWith("--port")) {
        instance.setPort(Integer.parseInt(arg.split("=")[1]));
      } else if (arg.startsWith("--httpFolder")) {
        instance.setHttpFolder(arg.split("=")[1]);
      } else {
        System.out.println("Argumento inválido: " + arg + "... Ignorado");
      }
    }

    return instance;
  }

  public static AppConfiguration getInstance() {
    if (instance == null) {
      instance = new AppConfiguration();
    }
    return instance;
  }

  public String getHttpFolder() {
    return httpFolder;
  }

  public int getPort() {
    return port;
  }

  public void setHttpFolder(String httpFolder) {
    this.httpFolder = httpFolder;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String buildFileName(String fileName) {
    return new StringBuilder().append(this.httpFolder).append("/").append(fileName).toString();
  }
}

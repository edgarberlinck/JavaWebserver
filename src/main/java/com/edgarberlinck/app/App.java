package com.edgarberlinck.app;

import java.io.IOException;
import com.edgarberlinck.app.config.AppConfiguration;
import com.edgarberlinck.app.server.*;

public class App {
  public static void main(String[] args) throws IOException {
    AppConfiguration config = AppConfiguration.getInstance(args);

    Server server = new HttpServer();
    System.out.println(new StringBuilder().append("Servidor ouvindo na porta ").append(config.getPort()).append("..."));

    server.start();
  }
}

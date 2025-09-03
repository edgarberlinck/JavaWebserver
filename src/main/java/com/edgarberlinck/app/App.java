package com.edgarberlinck.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.edgarberlinck.app.util.HttpRequestInfo;
import com.edgarberlinck.app.util.HttpResponseBuilder;

public class App {
  private static byte[] readResource(InputStream resource) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    byte[] data = new byte[4096]; // lê em blocos de 4 KB
    int n;
    while ((n = resource.read(data)) != -1) {
      buffer.write(data, 0, n);
    }
    return buffer.toByteArray();
  }

  public static void main(String[] args) throws IOException {
    ServerSocket server = new ServerSocket(8067);
    System.out.println("Servidor ouvindo na porta 8067...");

    while (true) {
      Socket client = server.accept(); // espera conexão
      InputStream in = client.getInputStream(); // bytes do browser
      OutputStream out = client.getOutputStream(); // pra responder

      StringBuilder request = new StringBuilder();
      int b;
      while ((b = in.read()) != -1) {
        request.append((char) b);

        // quebra quando achar \r\n\r\n (fim do header HTTP)
        if (request.toString().endsWith("\r\n\r\n")) {
          break;
        }
      }

      System.out.println("---- Requisição ----");
      System.out.println(request.toString());

      HttpRequestInfo info = new HttpRequestInfo(request.toString());
      // Resolve caminho do arquivo dentro da pasta www
      InputStream resource = App.class.getResourceAsStream("/www/" + info.getFileName());

      if (resource == null) {
        // 404
        new HttpResponseBuilder()
            .status(404, "Not Found")
            .contentType("text/html")
            .body("<h1>404 - Arquivo não encontrado</h1>")
            .write(out);
      } else {
        byte[] content = readResource(resource);

        new HttpResponseBuilder()
            .status(200, "OK")
            .contentType(info.getMimeType())
            .body(content)
            .write(out);

        resource.close();
      }

      out.flush();
      client.close();
    }
  }
}

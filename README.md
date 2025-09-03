# Java 4 Revival HTTP Server

## English

This tiny HTTP server is a project I originally built in college more than 20 years ago.  
Believe it or not, I recently found the original code buried in my email archive (I never delete anything), and it still ran without major changes.

To make it easier to use, I created a new Maven project around it — but the server logic is exactly the same as it was back then: raw sockets, manual parsing, and the simplest possible request/response handling.

It’s a great project for anyone studying the basics of HTTP, because you can actually **see how a browser talks to a server** without any framework or abstraction in the middle.

### Features

- Handles raw HTTP requests and responses
- Serves files from a `www` folder (HTML, CSS, JS, images, etc.)
- Responds with correct MIME types
- Includes a nostalgic hit of early-2000s coding style

### How to run

```bash
mvn compile exec:java
```

Then open [http://localhost:8067](http://localhost:8067) in your browser.

---

## Português

Este pequeno servidor HTTP foi um projeto que eu criei na faculdade há mais de 20 anos.  
Acredite se quiser: encontrei o código original perdido no meu email (sim, eu nunca apago emails), e ele ainda rodava sem grandes alterações.

Para facilitar o uso, criei um novo projeto Maven em volta dele — mas a lógica do servidor continua exatamente a mesma de antigamente: sockets crus, parsing manual e o tratamento mais simples possível de request/response.

É um excelente projeto para quem está estudando HTTP, pois você pode **ver como o navegador se comunica com o servidor** sem nenhuma camada de framework no meio.

### Funcionalidades

- Lê requisições e envia respostas HTTP na unha
- Serve arquivos da pasta `www` (HTML, CSS, JS, imagens, etc.)
- Retorna os MIME types corretos
- Traz aquela nostalgia do código raiz dos anos 2000

### Como rodar

```bash
mvn compile exec:java
```

Depois abra [http://localhost:8067](http://localhost:8067) no navegador.

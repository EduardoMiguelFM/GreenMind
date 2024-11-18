package br.com.fiap.GreenMind;

import br.com.fiap.GreenMind.filter.CORSFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jsonb.JsonBindingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    // Base URI que o servidor Grizzly irá escutar
    public static final String BASE_URI = "http://localhost:8080/";


    public static HttpServer startServer() {
        // Configuração do ResourceConfig, registrando o pacote correto
        final ResourceConfig rc = new ResourceConfig().packages("br.com.fiap.GreenMind.resource");
        rc.register(JsonBindingFeature.class); // Habilita suporte a JSON-B
        rc.register(CORSFilter.class); // Habilita o filtro CORS para requisições externas

        // Configura a URI base
        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8080"; // Porta padrão para desenvolvimento local
        }
        URI baseUri = URI.create("http://0.0.0.0:" + port + "/");

        return GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
    }

    public static void main(String[] args) throws IOException {
        // Inicia o servidor
        final HttpServer server = startServer();
        System.out.println(String.format(
                "Jersey app started with endpoints available at %s%nHit Ctrl-C to stop it...", BASE_URI
        ));

        // Mantenha o servidor rodando
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
    }
}

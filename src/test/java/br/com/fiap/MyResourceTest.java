package br.com.fiap;

import br.com.fiap.GreenMind.Main;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @BeforeEach
    public void setUp() throws Exception {
        // Inicializa o servidor
        server = Main.startServer();

        // Cria um cliente REST para comunicação
        Client c = ClientBuilder.newClient();

        // Define o alvo da API com a URL base do servidor
        target = c.target(Main.BASE_URI);
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Para o servidor após cada teste
        server.stop();
    }

    @Test
    public void testGetClientes() {
        // Substitua "clientes" pelo caminho correto do endpoint no Resource
        String responseMsg = target.path("clientes").request().get(String.class);

        // Substitua pela resposta real esperada do endpoint
        String expectedResponse = "Lista de clientes"; // Ajuste conforme necessário
        assertEquals(expectedResponse, responseMsg);
    }
}

package br.com.fiap.GreenMind.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*") // Aplica o filtro a todas as rotas
public class CORSConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização, caso necessário
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Configurar os cabeçalhos CORS
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, response); // Continua com a cadeia do filtro
    }

    @Override
    public void destroy() {
        // Cleanup, caso necessário
    }
}

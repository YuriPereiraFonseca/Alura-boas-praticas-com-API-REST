package med.voll.api.infra.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// serve pro spring carregar uma classe mas sem uma tarefa específica, componet é genêrico
@Component
public class SecurityFilter extends OncePerRequestFilter {

    // valida uma vez a cada requisição
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // seguindo fluxo da requisição
        filterChain.doFilter(request, response);
    }
}

package med.voll.api.infra.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// serve pro spring carregar uma classe mas sem uma tarefa específica, componet é genêrico
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    // valida uma vez a cada requisição
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // lógica de recuperar o token
        var tokenJWT = recuperarToken(request);

        // validar se token está correto
        var subject = tokenService.getSubject(tokenJWT);


        // seguindo fluxo da requisição
        filterChain.doFilter(request, response);

    }

    private String recuperarToken(HttpServletRequest request) {
        // se não existir cabeçalho vem nulo
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
        }

        return authorizationHeader.replace("Bearer ", "");
    }
}

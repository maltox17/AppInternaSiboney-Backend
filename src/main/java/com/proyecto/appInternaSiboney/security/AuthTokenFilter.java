package com.proyecto.appInternaSiboney.security;

import com.proyecto.appInternaSiboney.service.impl.UserDetailsImpl;
import com.proyecto.appInternaSiboney.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            
            // Agregar logging y validación de token
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String email = jwtUtils.getUserEmailFromJwtToken(jwt);
                logger.info("Email obtenido del JWT: {}", email);

                UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);
                Authentication authentication = jwtUtils.getAuthentication(userDetails, request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Autenticación establecida para el usuario: {}", email);
            } else {
                logger.error("No se pudo validar el JWT o el token es inválido");
            }
        } catch (Exception e) {
            logger.error("No se pudo establecer la autenticación de usuario: {}", e.getMessage());
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    // Método para extraer el JWT del encabezado de autorización
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);  // Extraer el token después de "Bearer "
        }

        return null;
    }
}


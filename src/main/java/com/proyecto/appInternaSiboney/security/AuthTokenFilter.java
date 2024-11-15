package com.proyecto.appInternaSiboney.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filtro que se ejecuta en cada solicitud para validar un token JWT y autenticar al usuario.
 * Extiende OncePerRequestFilter, asegurando que se ejecute una vez por solicitud.
 */
public class AuthTokenFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtUtils; 

  @Autowired
  private UserDetailsServiceImpl userDetailsService; 

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  /**
   * Mdtodo que intercepta cada solicitud y valida el token.
   * Si el token es válido, establece la autenticacion.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      // Extraer el token del encabezado de la solicitud
      String jwt = parseJwt(request);
      logger.debug("Token JWT recibido: " + jwt);


      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

  
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails,
            null, // No se necesita contraseña porque el token ya fue validado
            userDetails.getAuthorities()); // Establecer los roles/permisos del usuario

        // Añadir detalles adicionales de la solicitud
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // Configurar la autenticacion
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {

      logger.error("Cannot set user authentication: {}", e);
    }

    // Continuar con la cadena de filtros
    filterChain.doFilter(request, response);
  }

  /**
   * Extrae el token JWT del encabezado.
   * Solo procesa tokens que empiezan con Bearer.
   */
  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7); // Retornar solo el token sin el Bearer
    }

    return null; 
  }
}


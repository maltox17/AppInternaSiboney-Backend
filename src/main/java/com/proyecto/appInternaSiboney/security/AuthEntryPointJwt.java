package com.proyecto.appInternaSiboney.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase que maneja las excepciones de acceso no autorizado 401 en la aplicación.
 * Implementa AuthenticationEntryPoint para personalizar la respuesta cuando un usuario no autenticado intenta acceder.
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  // Logger para registrar errores de autenticación
  private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

  /**
   * Método llamado automaticamente cuando un usuario no autenticado intenta acceder a un recurso protegido.
   * Se personaliza la respuesta HTTP para incluir un mensaje de error en formato JSON.
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    // Registrar el mensaje de error en los logs
    logger.error("Unauthorized error: {}", authException.getMessage());

    // Configurar la respuesta como JSON y establecer el estado HTTP como 401
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    // Crear un cuerpo de respuesta con detalles del error
    final Map<String, Object> body = new HashMap<>();
    body.put("status", HttpServletResponse.SC_UNAUTHORIZED); // Código de estado HTTP
    body.put("error", "Unauthorized"); 
    body.put("message", authException.getMessage()); 
    body.put("path", request.getServletPath()); // Ruta solicitada por el cliente

    // Convertir el cuerpo de la respuesta a JSON y enviarlo al cliente
    final ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), body);
  }
}

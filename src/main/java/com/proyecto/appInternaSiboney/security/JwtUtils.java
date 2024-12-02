package com.proyecto.appInternaSiboney.security;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Clase para manejar operaciones relacionadas con JWT: creación, validación y extraer sus datos.
 */
@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


  //Clave secreta
  @Value("${jwt.secret}")
  private String jwtSecret;

  //24 horas
  @Value("${jwt.expiration.ms}")
  private int jwtExpirationMs;

  /**
   * Genera un token con los datos del usuario
   */
  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
            .setSubject(userPrincipal.getUsername()) 
            .claim("rol", userPrincipal.getRol()) 
            .claim("nombre", userPrincipal.getNombre()) 
            .claim("id", userPrincipal.getId()) 
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) 
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact(); // Genera el token
  }

  /**
   * Devuelve una clave secreta basada en la configuración definida
   */
  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)); // Decodifica y utiliza la clave secreta
  }

  /**
   * Obtiene el nombre de usuariodel token.
   */
  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
        .parseClaimsJws(token).getBody().getSubject(); // Extrae el username del token
  }

  /**
   * Valida el token JWT verificando su estructura, firma y expiración.
   */
  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken); // Valida el token
      return true; // Si no hay errores, el token es válido
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }
}


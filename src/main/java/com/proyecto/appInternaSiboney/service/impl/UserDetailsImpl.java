package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.entity.Empleado;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor que recibe los datos necesarios
    public UserDetailsImpl(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // Método estático para construir un UserDetailsImpl desde un Empleado
    public static UserDetailsImpl build(Empleado empleado) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(empleado.getRol().name()));

        return new UserDetailsImpl(
                empleado.getId(),
                empleado.getEmail(),
                empleado.getClave(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // Obtener el rol (en formato simple)
    public String getRol() {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);  // Devolver el primer rol (si solo hay uno)
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return email;  
    }
    
}
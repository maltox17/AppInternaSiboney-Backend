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
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor que recibe los datos necesarios
    public UserDetailsImpl(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;  // Este es el nombre de usuario, puede ser el nombre o el correo, según el diseño
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // Método estático para construir un UserDetailsImpl desde un Empleado
    public static UserDetailsImpl build(Empleado empleado) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + empleado.getRol().name()));

        return new UserDetailsImpl(
                empleado.getId(),
                empleado.getNombre(),  // O usa empleado.getEmail() si prefieres el correo como nombre de usuario
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
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
}

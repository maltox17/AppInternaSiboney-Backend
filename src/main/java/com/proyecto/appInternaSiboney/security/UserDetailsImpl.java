package com.proyecto.appInternaSiboney.security;

import com.proyecto.appInternaSiboney.entity.Empleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Método estático para construir UserDetailsImpl a partir de Empleado
    public static UserDetailsImpl build(Empleado empleado) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(empleado.getRol().name()));
        return new UserDetailsImpl(
            empleado.getId(), 
            empleado.getUsername(), 
            empleado.getClave(), 
            authorities
        );
    }


    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return true;
    }
}
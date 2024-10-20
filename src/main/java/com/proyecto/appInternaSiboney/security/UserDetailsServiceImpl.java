package com.proyecto.appInternaSiboney.security;

import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado con username: " + username));
    

        return UserDetailsImpl.build(empleado);
    }
}



package com.proyecto.appInternaSiboney.service.impl;

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

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByEmail(nombre)
                .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado"));
        return UserDetailsImpl.build(empleado);
    }
}

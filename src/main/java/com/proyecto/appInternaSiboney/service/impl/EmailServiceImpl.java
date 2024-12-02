package com.proyecto.appInternaSiboney.service.impl;

import com.proyecto.appInternaSiboney.dto.EmailIndividualDTO;
import com.proyecto.appInternaSiboney.dto.EmailMasivoDTO;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Rol;
import com.proyecto.appInternaSiboney.excepcion.IdNotFoundException;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
import com.proyecto.appInternaSiboney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

    // Configura y envia los correos
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public void enviarCorreoIndividual(EmailIndividualDTO emailDTO) throws MessagingException {

        // representa un correo MIME
        MimeMessage message = mailSender.createMimeMessage();

        // El Helper simplifica la creación del mensaje y permite añadir detalles como adjuntos.
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Empleado empleado = empleadoRepository.findById(emailDTO.getEmpleadoId())
                .orElseThrow(IdNotFoundException::new);

        // Configuramos los detalles del correo
        helper.setTo(empleado.getEmail());
        helper.setSubject(emailDTO.getAsunto());
        helper.setText(emailDTO.getMensaje(), false);

        mailSender.send(message);
    }

    @Override
    public void enviarCorreoMasivo(EmailMasivoDTO emailDTO) throws MessagingException {
        List<Empleado> empleados = empleadoRepository.findAll();

        //Filtramos los empleados que no sean Jefes
        List<Empleado> empleadosNoJefes = empleados.stream()
                .filter(empleado -> !Rol.JEFE.equals(empleado.getRol()))
                .collect(Collectors.toList());

        for (Empleado empleado : empleadosNoJefes) {

            //Creamos el mensaje y lo configuramos con el helper
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(empleado.getEmail());
            helper.setSubject(emailDTO.getAsunto());
            helper.setText(emailDTO.getMensaje(), false);
            mailSender.send(message);

        }

    }
}

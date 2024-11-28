package com.proyecto.appInternaSiboney.controller;

import com.proyecto.appInternaSiboney.dto.EmailIndividualDTO;
import com.proyecto.appInternaSiboney.dto.EmailMasivoDTO;
import com.proyecto.appInternaSiboney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/individual")
    public ResponseEntity<String> enviarCorreoIndividual(@RequestBody @Valid EmailIndividualDTO emailDTO) {

        try {
            emailService.enviarCorreoIndividual(emailDTO);
            return ResponseEntity.ok("Correo enviado con éxito.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar el correo: " + e.getMessage());
        }
    }

    @PostMapping("/masivo")
    public ResponseEntity<String> enviarCorreoMasivo(@RequestBody EmailMasivoDTO emailDTO) {
        try {
            emailService.enviarCorreoMasivo(emailDTO);
            return ResponseEntity.ok("Correos masivos enviados con éxito.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar correos masivos: " + e.getMessage());
        }
    }
}

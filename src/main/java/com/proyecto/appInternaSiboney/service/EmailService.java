package com.proyecto.appInternaSiboney.service;

import com.proyecto.appInternaSiboney.dto.EmailIndividualDTO;
import com.proyecto.appInternaSiboney.dto.EmailMasivoDTO;

import jakarta.mail.MessagingException;



public interface EmailService {
    void enviarCorreoIndividual(EmailIndividualDTO emailDTO) throws MessagingException;
    void enviarCorreoMasivo(EmailMasivoDTO emailDTO) throws MessagingException;
}


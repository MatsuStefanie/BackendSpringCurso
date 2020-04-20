package com.cristal.stefanie.cursomc.services;

import com.cristal.stefanie.cursomc.domain.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService implements EmailService{

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido obj) {

    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulando o envio de email HTML...");
        LOG.info(msg.toString());
        LOG.info("Email enviado!!!");
    }

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {

    }

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando o envio de email...");
        LOG.info(msg.toString());
        LOG.info("Email enviado!!!");
    }
}

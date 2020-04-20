package com.cristal.stefanie.cursomc.services;

import com.cristal.stefanie.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(simpleMailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(obj.getCliente().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Pedido confirmado! Código: " + obj.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(obj.toString());
        return simpleMailMessage;
    }

    protected String htmlFromTemplatePedido(Pedido obj) {
        Context context = new Context();
            context.setVariable("pedido", obj);
        return templateEngine.process("email/confirmacaoPedido", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido obj) {
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = prepareMimeMessageFromPedido(obj);
            sendHtmlEmail(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sendOrderConfirmationEmail(obj);
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(obj.getCliente().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Pedido Confirmado! Código: "+obj.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplatePedido(obj), true);
        return mimeMessage;

    }
}

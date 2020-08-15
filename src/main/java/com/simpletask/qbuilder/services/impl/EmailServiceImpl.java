package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender emailsender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.emailsender=javaMailSender;
    }
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailsender.send(message);
    }

    @Override
    public void sendMultiplySimpleMessage(String[] to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailsender.send(message);
    }
}

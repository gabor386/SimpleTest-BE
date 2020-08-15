package com.simpletask.qbuilder.services;

public interface EmailService {
    void sendSimpleMessage(String to,String subject, String text);
    void sendMultiplySimpleMessage(String[]to,String subject, String text);
}

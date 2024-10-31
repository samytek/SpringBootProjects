package com.example.demo.service;

public interface SendTestEmail {

    public void sendEmailWithAttachment(String to, String subject, String text, String attachmentPath) throws Exception;
    public void sendSimpleEmail(String to, String subject, String text);
}

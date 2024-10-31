package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendTestEmailImpl implements SendTestEmail {

	@Autowired
	private JavaMailSender mailSender;

	@Value("$(spring.mail.username)")
	private String fromEmailId;

	@Override
	public void sendEmailWithAttachment(String to, String subject, String text, String attachmentPath)
			throws Exception {
//		
//		MimeMessage message = mailSender.createMimeMessage();
//
//		MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//		helper.setTo(to);
//		helper.setSubject(subject);
//		helper.setText(text);
//		helper.setFrom("your-email@gmail.com");
//
//		File attachment = new File(attachmentPath);
//		helper.addAttachment(attachment.getName(), attachment);
//
//		mailSender.send(message);
	}

	@Override
	public void sendSimpleEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		message.setFrom(fromEmailId);
		mailSender.send(message);
	}
}

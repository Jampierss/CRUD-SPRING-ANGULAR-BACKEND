package com.tevology.logistica.models.services;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSender;

public interface IEmailService {
	
	public JavaMailSender getJavaMailSender(String host, Integer port, String username, String password, 
											String protocol,Boolean blnStartTls, String hostSslTrust);
	
	public void sendEmailCustomSender(JavaMailSender customMailSender,String customFrom,String to,String subject,
										   String content, String rutaBase, List<String> correos, List<String> archivos) throws MessagingException, IOException;
	public void sendEmail(String to, String subject, String content, String rutaBase, List<String> correos, 
										   List<String> archivos) throws MessagingException, IOException;

}

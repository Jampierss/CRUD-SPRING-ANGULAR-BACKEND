package com.tevology.logistica.models.services;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@SuppressWarnings("unused")
	@Autowired
    private TemplateEngine templateEngine;
	
	@Value("${spring.mail.username}")
    private String from;
	
	@SuppressWarnings("unused")
	private final static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	
	@Override
	public JavaMailSender getJavaMailSender(String host, Integer port, String username, String password, 
											String protocol,Boolean blnStartTls, String hostSslTrust) {
	    JavaMailSenderImpl customMailSender = new JavaMailSenderImpl();
	    customMailSender.setHost(host);
	    customMailSender.setPort(port);
	    
	    customMailSender.setUsername(username);
	    customMailSender.setPassword(password);
	    
	    Properties props = customMailSender.getJavaMailProperties();
	    props.put("mail.defaultEncoding", "UTF-8");	
	    props.put("mail.transport.protocol", protocol);
	    
	    if(protocol.equals("smtp")) {
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", blnStartTls?"true":"false");
		    props.put("mail.smtp.ssl.trust", blnStartTls?hostSslTrust:"");	 
		    props.put("mail.smtp.connectiontimeout", 15000);	 
		    props.put("mail.smtp.timeout", 15000);	 
		    props.put("mail.smtp.writetimeout", 15000);	
	    }
	    
	    //props.put("mail.debug", "true");
	    
	    return customMailSender;
	}
    
	@Override
	public void sendEmailCustomSender(JavaMailSender customMailSender,String customFrom,String to,String subject,
										   String content,String rutaBase ,List<String> correos, List<String> archivos) throws MessagingException, IOException {

        MimeMessage message = customMailSender.createMimeMessage();
        
        try {
        	MimeMessageHelper helper = new MimeMessageHelper(message, true);
        	  
        	if(correos == null) {
        		correos = new ArrayList<>();
        	}
        	correos.add(0, to);
        	
            helper.setFrom(customFrom);
            helper.setTo(correos.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(content);
            
            if(archivos != null && archivos.size() > 0) {
                for (int i = 0; i < archivos.size(); i++) {
                	Path ruta = Paths.get(rutaBase).resolve(archivos.get(i)).toAbsolutePath();
                	byte[] contByte = Files.readAllBytes(ruta);
                    helper.addAttachment(archivos.get(i), new ByteArrayResource(contByte));
    			}               	
            } 

            customMailSender.send(message);
            
        } catch (MessagingException e) {
            e.printStackTrace();
        	throw e;
        } catch (IOException e) {
			e.printStackTrace();
        	throw e;
		}
	}

	@Override
	public void sendEmail(String to, String subject, String content, String rutaBase, 
						  List<String> correos, List<String> archivos) throws MessagingException, IOException {
		try {
			this.sendEmailCustomSender(mailSender, from, to, subject, content, rutaBase , correos, archivos);
        } catch (MessagingException e) {
            e.printStackTrace();
        	throw e;
        } catch (IOException e) {
			e.printStackTrace();
        	throw e;
		}
	}	
}

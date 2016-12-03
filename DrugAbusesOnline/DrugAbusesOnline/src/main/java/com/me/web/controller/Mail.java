package com.me.web.controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Mail {
    
    private Session sesion;
    public Mail() {
        
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        	
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication("donotreplydubs@gmail.com", "ganydakshin");
                    }
                    
                }
                               
                );
        sesion = session;
        
    }
    
    public void sendMail(String emailID, String customerName) {
        String to = emailID; 
        String name = customerName;
        
        
        
        try {
            Message message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress("donotreplydubs@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("DRUG ONLINE PORTAL");
            
                try {                
                    message.setText("Hi "+customerName+"Thank you for signing up");
                    Transport.send(message);
                    JOptionPane.showMessageDialog(null, "Email sent successfully");
                   
                }
                catch(AddressException e) {
                    JOptionPane.showMessageDialog(null, "This email address is invalid");
                }
           
        } catch(Exception e) {
             
            JOptionPane.showMessageDialog(null, e);
        }
    }
  
}
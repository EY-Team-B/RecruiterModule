package com.recruitment;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("preparing to send message...");
        System.out.println( "Hello World!" );
        String message="Hello, people";
        String subject="RecruitmentTracker: Confirmation";
        String to="tridishag479@gmail.com";
        String to1="sd7201@srmist.edu.in";
        String to2="tg8316@srmist.edu.in";
        String from="recruitmenttrack2022@gmail.com";
        
       // sendEmail(message,subject,to,from);
        sendAttach(message,subject,to,to1,to2,from);   //to candidate and panelist
    }

    //this is responsible to send the message with attachment
    private static void sendAttach(String message, String subject, String to, String to1, String to2, String from) {
    //Variable for gmail 
      String host="smtp.gmail.com";
      
      //get the system properties
      Properties properties=System.getProperties();
      System.out.println("PROPERTIES "+properties);
      
      //setting important information to properties object
      
      //host set
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "465");
      properties.put("mail.smtp.ssl.enable", "true");
      properties.put("mail.smtp.auth", "true");
      
      //Step 1: To get the session object..
      Session session=Session.getInstance(properties,new Authenticator() {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          
          return new PasswordAuthentication("recruitmenttrack2022@gmail.com", "Recruitment@2022");
        }
        
      });
      
      session.setDebug(true);
      
      //Step 2: Compose the message[text, multi media]
      MimeMessage m = new MimeMessage(session);
      
      
      try {
      //from email id
        m.setFrom(from);
        
        //adding recipient
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(to1));
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(to2));
        
        //adding subject to message
        m.setSubject(subject);
        
        //attachment..
        //file path
        String path="C:\\Users\\ASUS\\Desktop\\team.png";
        
        MimeMultipart mimeMultipart= new MimeMultipart();
        
        //text       
        MimeBodyPart textMime= new MimeBodyPart();
        
      //file
        MimeBodyPart fileMime= new MimeBodyPart();
        
        try {
          textMime.setText(message);
          
          File file=new File(path);
          fileMime.attachFile(file);
          
          mimeMultipart.addBodyPart(textMime);
          mimeMultipart.addBodyPart(fileMime);
          
        } catch (Exception e) {
          
          e.printStackTrace();
        } 
        
        m.setContent(mimeMultipart);
        
      //send
        //Step 3: send the message using Transport class
        Transport.send(m);
        
      }catch (Exception e) {
        
        e.printStackTrace();
      }
        
        
        
        
        
        
        System.out.println("Sent successfully!!");
        
      } 
      
    

    //this is responsible to send email..
    private static void sendEmail(String message, String subject, String to, String to1, String to2, String from) {
      //Variable for gmail 
      String host="smtp.gmail.com";
      
      //get the system properties
      Properties properties=System.getProperties();
      System.out.println("PROPERTIES "+properties);
      
      //setting important information to properties object
      
      //host set
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "465");
      properties.put("mail.smtp.ssl.enable", "true");
      properties.put("mail.smtp.auth", "true");
      
      //Step 1: To get the session object..
      Session session=Session.getInstance(properties,new Authenticator() {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          
          return new PasswordAuthentication("recruitmenttrack2022@gmail.com", "Recruitment@2022");
        }
        
      });
      
      session.setDebug(true);
      
      //Step 2: Compose the message[text, multi media]
      MimeMessage m = new MimeMessage(session);
      
      
      try {
      //from email id
        m.setFrom(from);
        
        //adding recipient
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(to1));
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(to2));
        
        //adding subject to message
        m.setSubject(subject);
        
        //adding text to message
        m.setText(message);
        
        //send
        //Step 3: send the message using Transport class
        Transport.send(m);
        System.out.println("Sent successfully!!");
        
      } catch (MessagingException e) {
        
        e.printStackTrace();
      }
      
    }
}

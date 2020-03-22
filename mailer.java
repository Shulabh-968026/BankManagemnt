package bank;

import java.util.Properties;  
import javax.activation.*;
import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class mailer {  
public static void send(){ 
	//String host="localhost";
final String user="shulabhdixit23498@gmail.com"; 
final String pass="6350272046";
final String to="shulabhdixit23498@gmail.com";
String subject="bank information";
String msg="your new account is open in state bank of india";
//1st step) Get the session object    
Properties props = new Properties(); 
//props.setProperty("mail.smtp.host","smtp.hmail.com");
//props.setProperty("mail.smtp.port","587");
//props.setProperty("mail.smtp.socketFactory.port","587");
props.put("mail.smtp.host","smtp.gmail.com");  
props.put("mail.smpt.port", "587");
props.put("mail.smtp.auth", "true"); 
props.put("mail.smpt.starttls.enable","true");  

Session session = Session.getDefaultInstance(props,  
 new javax.mail.Authenticator() {  
  protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user,pass);  
   }  
});  
//2nd step)compose message  
try {  
 MimeMessage message = new MimeMessage(session);  
 message.setFrom(new InternetAddress(user));  
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
 message.setSubject(subject);  
 message.setText(msg);  
   
 //3rd step)send message  
 Transport.send(message);  
  
 System.out.println("Done");  
  
 } catch (MessagingException e) {  
    e.printStackTrace(); 
    System.out.println(e.getMessage());
 }  
      
}  
}  

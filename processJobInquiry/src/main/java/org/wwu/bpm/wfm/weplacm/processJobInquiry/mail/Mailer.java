package org.wwu.bpm.wfm.weplacm.processJobInquiry.mail;

import java.security.GeneralSecurityException;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;

import com.sun.mail.util.MailSSLSocketFactory;

   
public class Mailer{  
	
	public static String REJECTION_MSG = "Sehr geehrte/r %s " +
          ",\nwir haben Ihre Bewerbung für  %s erhalten. "
          + "\nLeider müssen wir Ihnen mitteilen, dass wir uns für einen anderen Bewerber entschieden haben."
          + "\nWir wünschen Ihnen für Ihre weitere berufliche und private Zukunft alles Gute und viel Erfolg"
          + "\nIhr weplacm Recruiting Team";
	
	public static String INVITATION_MSG = "Sehr geehrte/r %s " +
	          ",\nwir haben notiert, dass Sie interesse an zukünftigen Job-Angeboten haben. In diesem Zusammenhang haben wir"
	          + "ein für sie attraktives Jobangebot gefunden: %s erhalten. "
	          + "\nDieses finden sie hier: %s"
	          + "\nWir hoffen, dass dieses Angebot für sie ansprechend is. Viel Erfolg"
	          + "\nIhr weplacm Recruiting Team";
	
    public static void send(final String from,final String password,String to,String subject,String applicant, String topic, int msgtype){  
          
        //Mailer.send("recruiting.weplacm@gmail.com","DieAntwortIst42","Toby.Mai@web.de", header, mailMessage);
        //Get properties object    
          Properties props = new Properties();


	  		props.put("mail.smtp.auth", "true");
	  		props.put("mail.smtp.starttls.enable", "true");
	  		props.put("mail.smtp.host", "smtp.gmail.com");
	  		props.put("mail.smtp.port", "587");
	  		props.put("mail.smtp.checkserveridentity", "false");
	  		props.put("mail.smtp.ssl.trust", "*");
	  		
	  		MailSSLSocketFactory socketFactory;
			try {
				socketFactory = new MailSSLSocketFactory();
		  		socketFactory.setTrustAllHosts(true);
		  		props.put("mail.smtp.ssl.socketFactory", socketFactory);
			} catch (GeneralSecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

          
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(subject);
           String msg = "";
           if (msgtype == 1) {
        	   msg = String.format(REJECTION_MSG, applicant, topic);
           } else {
        	   msg = String.format(INVITATION_MSG, applicant, topic, "http://localhost:8080/camunda/app/welcome/default/#/welcome");
           }
           message.setText(msg);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  
//    String header;
//    String mailMessage;
//    String name, bewerberAdresse, kunde;
//    
//    kunde = "xyz Technologys";
//    name = "Tobias M.";
//    bewerberAdresse = "Toby.Mai@web.de";
//    header = "Ihre Bewerbung bei " + kunde;
//    mailMessage = "Sehr geehrte/r " + name + 
//            ",\nwir haben Ihre Bewerbung für " + kunde + " erhalten. "
//            + "\nLeider müssen wir Ihnen mitteilen, dass wir uns für einen anderen Bewerber entschieden haben."
//            + "\nWir wünschen Ihnen für Ihre weitere berufliche und private Zukunft alles Gute und viel Erfolg"
//            + "\nIhr weplacm Recruiting Team";
//    
//    Mailer.send("recruiting.weplacm@gmail.com","DieAntwortIst42", bewerberAdresse, header, mailMessage);
} 

package org.wwu.bpm.wfm.weplacm.processJobInquiry.mail;

import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;    
import javax.mail.internet.*;

import com.sun.mail.util.MailSSLSocketFactory;

   
public class Mailer{  
	
	public static Boolean isValidMail(String email) {
		final String MAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(MAIL_REGEX); 
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	
	public static String REJECTION_MSG = "Sehr geehrte/r %s " +
          ",\nwir haben Ihre Bewerbung f�r  %s erhalten. "
          + "\nLeider m�ssen wir Ihnen mitteilen, dass wir uns f�r einen anderen Bewerber entschieden haben."
          + "\nWir w�nschen Ihnen f�r Ihre weitere berufliche und private Zukunft alles Gute und viel Erfolg"
          + "\nIhr weplacm Recruiting Team";
	
	public static String INVITATION_MSG = "Sehr geehrte/r %s " +
	          ",\nwir haben notiert, dass Sie interesse an zuk�nftigen Job-Angeboten haben. In diesem Zusammenhang haben wir"
	          + "ein f�r sie attraktives Jobangebot gefunden: %s . "
	          + "\nDieses finden sie hier: %s"
	          + "\nWir hoffen, dass dieses Angebot f�r sie ansprechend is. Viel Erfolg"
	          + "\nIhr weplacm Recruiting Team";
	
    public static void send(final String from,final String password,String to,String subject,String applicant, String topic, int msgtype, String optionalUrl){  
          if (isValidMail(to)) {
        	  

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
        	   msg = String.format(INVITATION_MSG, applicant, topic, optionalUrl);
           }
           message.setText(msg);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
      } 
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
//            ",\nwir haben Ihre Bewerbung f�r " + kunde + " erhalten. "
//            + "\nLeider m�ssen wir Ihnen mitteilen, dass wir uns f�r einen anderen Bewerber entschieden haben."
//            + "\nWir w�nschen Ihnen f�r Ihre weitere berufliche und private Zukunft alles Gute und viel Erfolg"
//            + "\nIhr weplacm Recruiting Team";
//    
//    Mailer.send("recruiting.weplacm@gmail.com","DieAntwortIst42", bewerberAdresse, header, mailMessage);
} 

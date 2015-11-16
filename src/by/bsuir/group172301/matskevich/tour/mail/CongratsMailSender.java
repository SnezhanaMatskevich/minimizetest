/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.group172301.matskevich.tour.mail;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import by.bsuir.group172301.matskevich.tour.resource.PathManager;
public class CongratsMailSender {
    
     
	String senderAddress;
	String senderPass;
	String uName;
	String url;

	public CongratsMailSender() {
		senderAddress = "snezhana.matskevich@gmail.com";
		senderPass = "66717516671751";
		uName = "snezhana.matskevich";
		url = "";
	}

	public void sendMail(String way) throws AddressException,
			MessagingException, UnsupportedEncodingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(uName, senderPass);
					}
				});
                
                
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(senderAddress));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(way));
		message.setSubject("Поздравляем с успешной регистрацией");
		message.setText("Дорогой пользователь,\" + \"\\n\\n Вы успешно зарегистрировались на Booking.by.");
		Transport.send(message);
		System.out.println("Done");

	}

}
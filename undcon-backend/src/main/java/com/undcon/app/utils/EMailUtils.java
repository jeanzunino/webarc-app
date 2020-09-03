package com.undcon.app.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EMailUtils {
	/**
	 * @param smtp
	 * @param subject
	 * @param to
	 * @param from
	 * @param message
	 * @param user
	 * @param password
	 */
	public static void sendMail(String smtp, String subject, String[] to, String from, String message, String user,
			String password) {

		//TODO Envio do email não está funcionando
//		HtmlEmail htmlEmail = new HtmlEmail();
//		try {
//			htmlEmail.setHtmlMsg(message);
//			htmlEmail.addTo(to);
//			htmlEmail.setSubject(subject);
//			htmlEmail.setFrom(from);
//			htmlEmail.setHostName(smtp);
//			htmlEmail.setSmtpPort(465);
//			htmlEmail.setTLS(true);
//			htmlEmail.setSSL(true);
//			htmlEmail.setAuthenticator(new DefaultAuthenticator(user, password));
//			htmlEmail.send();
//		} catch (EmailException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		Transport trans = null;
//		try {
//			Properties p = new Properties();
//			p.put("mail.host", smtp);
//			p.put("mail.smtp.starttls.enable", "true");
//			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//			Session session = Session.getInstance(p, null);
//			MimeMessage msg = new MimeMessage(session);
//
//			// "de" e "para"!!
//			msg.setFrom(new InternetAddress(from));
//
//			String recAdds = "";
//			for (String email : to) {
//				recAdds += email + ",";
//			}
//			if (to.length > 0)
//				recAdds = recAdds.substring(0, recAdds.length() - 1);
//			final Address[] recAdd = InternetAddress.parse(recAdds);
//			msg.setRecipients(Message.RecipientType.TO, recAdd);
//			msg.setSentDate(new Date());
//			msg.setSubject(subject);
//			msg.setContent("<h1>Hello world</h1>", "text/html");
//			msg.setText(message);
//			trans = session.getTransport("smtps");
//			trans.connect(smtp, user, password);
//			trans.sendMessage(msg, recAdd);
//			trans.close();
//			System.out.println("E-mail enviado com sucesso");
//		} catch (AddressException adrEx) {
//			if (trans.isConnected()) {
//				try {
//					trans.close();
//				} catch (MessagingException e) {
//					e.printStackTrace();
//				}
//			}
//			adrEx.printStackTrace();
//			throw new IllegalArgumentException("AddressException: Cannot send message.\n" + adrEx.getMessage());
//		} catch (MessagingException msgEx) {
//			if (trans.isConnected()) {
//				try {
//					trans.close();
//				} catch (MessagingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			msgEx.printStackTrace();
//			throw new IllegalArgumentException("MessagingException: Cannot send message.\n" + msgEx.getMessage());
//		}
	}
	
	public static void main(String[] args) {
		String smtp = "smtp.gmail.com";
//		String smtp = "smtp.googlemail.com";
		String from = "jean.zunino@gmail.com";
		String email = "jean.zunino@gmail.com";
		
		//TODO Remover senha do código, passar para configuração
		String password = "";

		String subject = "Undcon - Cadastro de Tenant";
		String[] toEmails = {"jean.zunino@gmail.com"};
		sendMail(smtp, subject, toEmails, from, subject, from, password);
	}
}

package com.undcon.app.services;

import org.springframework.stereotype.Component;

import com.undcon.app.utils.EMailUtils;

@Component
public class EmailSenderService {

	public void sendEmail(String message) {
		String smtp = "smtp.gmail.com";
//		String smtp = "smtp.googlemail.com";
		String from = "jean.zunino@gmail.com";
		String email = "jean.zunino@gmail.com";
		
		//TODO Remover senha do código, passar para configuração
		String password = "";

		String subject = "Undcon - Cadastro de Tenant";
		String[] toEmails = {"jean.zunino@gmail.com"};
		
		EMailUtils.sendMail(smtp, subject, toEmails, from, message, email, password);
	}
}

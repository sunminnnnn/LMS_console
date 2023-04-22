package Mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailConfig extends Authenticator {

	PasswordAuthentication authentication;

	public MailConfig() {

		String id = "ribbon0508@gmail.com";
		String pwd = "pjhyqsmzsjvcydrg";

		authentication = new PasswordAuthentication(id, pwd);
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return authentication;
	}

}
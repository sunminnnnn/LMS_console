package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import valueObject.VUserInfo;

public class MailSender {
	Session session;

	public MailSender() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		this.session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ribbon0508@gmail.com", "pjhyqsmzsjvcydrg");
			}
		});
	}

	public void sendEmailToFindId(VUserInfo vUser) throws MessagingException {
		String receiver = vUser.getEMail();
		String title = "명지대학교 수강신청 - 아이디 찾기";
		String content = "귀하의 아이디는 " + vUser.getId() + "입니다.";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("ribbon0508@gmail.com", "명지대학교 수강신청", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmailToFindPwd(VUserInfo vUser) throws MessagingException {
		String receiver = vUser.getEMail();
		String title = "명지대학교 수강신청 - 비밀번호 찾기";
		String content = "귀하의 비밀번호는 " + vUser.getPassword() + "입니다.\n분실 위험이 있으므로 변경을 권장합니다.";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("ribbon0508@gmail.com", "명지대학교 수강신청", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");
			
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
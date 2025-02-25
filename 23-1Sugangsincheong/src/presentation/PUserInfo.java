package presentation;

import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import controller.CJoin;
import controller.CLogin;
import controller.CUserInfo;
import mail.MailSender;
import valueObject.VAccount;
import valueObject.VEMail;
import valueObject.VUserInfo;

public class PUserInfo {
	private CJoin cJoin;
	private CUserInfo cUserInfo;
	private MailSender mailSender;
	private Scanner scanner;

	public PUserInfo(Scanner scanner) {
		this.cJoin = new CJoin();
		this.mailSender = new MailSender();
		this.scanner = scanner;
		this.cUserInfo = new CUserInfo();
	}

	public void show() {
		Vector<VAccount> vAccountList = this.cJoin.readAll();
		for (VAccount one : vAccountList) {
			System.out.println(one.getId() + " " + one.getName() + " " + one.getDepartment());
		}
	}

	public void findID() {
		try {
			boolean isValidEmail = false;
			String eMail = "";
			while (!isValidEmail) {
				System.out.println(global.Locale.USERINFO.ENTER_EMAIL);
				eMail = this.scanner.next();
				if (isEmail(eMail)) {
					isValidEmail = true;
				} else {
					System.out.println(global.Locale.USERINFO.ERROR_EMAIL);
				}
			}

			VEMail vEMail = new VEMail();
			vEMail.setEMail(eMail);
			if (cUserInfo.ExistUserByEmail(vEMail)) {
				VUserInfo vUser = cUserInfo.getUserByEmail(vEMail);

				mailSender.sendEmailToFindId(vUser);
				System.out.println(global.Locale.USERINFO.SEND_EMAIL_ID);
			} else {
				System.out.println(global.Locale.USERINFO.NOT_EXIST_EMAIL);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void findPassword() {
		// TODO Auto-generated method stub
		try {
			boolean isValidEmail = false;
			String eMail = "";
			while (!isValidEmail) {
				System.out.println(global.Locale.USERINFO.ENTER_EMAIL);
				eMail = this.scanner.next();
				if (isEmail(eMail)) {
					isValidEmail = true;
				} else {
					System.out.println(global.Locale.USERINFO.ERROR_EMAIL);
				}
			}
			System.out.println(global.Locale.USERINFO.ENTER_ID);
			String ID = this.scanner.next();

			VEMail vEMail = new VEMail();
			vEMail.setEMail(eMail);

			if (cUserInfo.ExistUserByEmailAndId(vEMail, ID)) {
				VUserInfo vUserInfo = cUserInfo.getUserByEmail(vEMail);

				// 여기서 임시비밀번호로 바꿈
				String tempPassword = getRamdomPassword(10);
				CLogin cLogin = new CLogin();
				vUserInfo = cLogin.tempPassWord(ID, tempPassword);

				mailSender.sendEmailToFindPwd(vUserInfo);
				System.out.println(global.Locale.USERINFO.SEND_EMAIL_PASSWORD);
			} else {
				System.out.println(global.Locale.USERINFO.ERROR_EMAIL);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void changePassword() { // 로그인 후에
		try {
			System.out.println(global.Locale.USERINFO.ENTER_NOWPASSWORD);
			String nowPassword = this.scanner.next();
			System.out.println(global.Locale.USERINFO.ENTER_TEMPPASSWORD);
			String tempPassword = this.scanner.next();

			VUserInfo vUserInfo = cUserInfo.getUserByPassword(nowPassword);
			CLogin cLogin = new CLogin();
			vUserInfo = cLogin.changePassword(nowPassword, tempPassword);
			System.out.println(global.Locale.USERINFO.FIN_PASSWORD_CHANGE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isEmail(String str) {
		return Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", str);
	}

	public String getRamdomPassword(int size) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '!', '@', '#', '$', '%', '^',
				'&' };
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());

		int idx = 0;
		int len = charSet.length;
		for (int i = 0; i < size; i++) {
			idx = sr.nextInt(len); // 강력한 난수를 발생시키기 위해 SecureRandom을 사용
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

}

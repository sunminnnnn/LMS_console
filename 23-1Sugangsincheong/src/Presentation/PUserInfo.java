package Presentation;

import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import Controller.CJoin;
import Controller.CLogin;
import Controller.CUserInfo;
import Mail.MailSender;
import ValueObject.VAccount;
import ValueObject.VEMail;
import ValueObject.VUserInfo;

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
				System.out.println(Global.Locale.USERINFO.ENTER_EMAIL);
				eMail = this.scanner.next();
				if (isEmail(eMail)) {
					isValidEmail = true;
				} else {
					System.out.println(Global.Locale.USERINFO.ERROR_EMAIL);
				}
			}

			VEMail vEMail = new VEMail();
			vEMail.setEMail(eMail);
			if (cUserInfo.ExistUserByEmail(vEMail)) {
				VUserInfo vUser = cUserInfo.getUserByEmail(vEMail);

				mailSender.sendEmailToFindId(vUser);
				System.out.println(Global.Locale.USERINFO.SEND_EMAIL_ID);
			} else {
				System.out.println(Global.Locale.USERINFO.NOT_EXIST_EMAIL);
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
				System.out.println(Global.Locale.USERINFO.ENTER_EMAIL);
				eMail = this.scanner.next();
				if (isEmail(eMail)) {
					isValidEmail = true;
				} else {
					System.out.println(Global.Locale.USERINFO.ERROR_EMAIL);
				}
			}
			System.out.println(Global.Locale.USERINFO.ENTER_ID);
			String ID = this.scanner.next();

			VEMail vEMail = new VEMail();
			vEMail.setEMail(eMail);

			if (cUserInfo.ExistUserByEmailAndId(vEMail, ID)) {
				VUserInfo vUserInfo = cUserInfo.getUserByEmail(vEMail);

				// 여기서 임시비밀번호로 바꿈
				String tempPassword = getRamdomPassword(10);
				CLogin cLogin = new CLogin();
				cLogin.delete(vUserInfo);
				vUserInfo = cLogin.tempPassWord(ID, tempPassword);

				mailSender.sendEmailToFindPwd(vUserInfo);
				System.out.println(Global.Locale.USERINFO.SEND_EMAIL_PASSWORD);
			} else {
				System.out.println(Global.Locale.USERINFO.ERROR_EMAIL);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void changePassword() { // 로그인 후에
		try {
			System.out.println("현재 비밀번호를 입력하세요: ");
			String nowPassword = this.scanner.next();
			VUserInfo vUserInfo = cUserInfo.getUserByPassword(nowPassword);
			System.out.println("변경할 비밀번호를 입력하세요: ");
			String tempPassword = this.scanner.next();
			CLogin cLogin = new CLogin();
			cLogin.delete(vUserInfo);
			vUserInfo = cLogin.changePassword(nowPassword, tempPassword);
			
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

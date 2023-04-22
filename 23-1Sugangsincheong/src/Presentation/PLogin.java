package Presentation;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Controller.CLogin;
import Main.Main;
import ValueObject.VLogin;
import ValueObject.VUserInfo;

public class PLogin {
	private Scanner scanner;

	public PLogin(Scanner scanner) {
		this.scanner = scanner;
	}

	public VUserInfo run() {
		SimpleDateFormat sd = new SimpleDateFormat(Global.Locale.LOGIN.TIME_FORMAT);

		// get userId&password from user
		System.out.println(Global.Locale.LOGIN.ID);
		String userId = scanner.next();
		System.out.println(Global.Locale.LOGIN.PASSWORD);
		String password = scanner.next();
		String password_decrypt = Main.SHA256(password);

		// generate value object
		VLogin vLogin = new VLogin();
		vLogin.setUserId(userId);
		vLogin.setPassword(password_decrypt);

		// pass VLogin to CLogin
		CLogin cLogin = new CLogin();
		VUserInfo vUserInfo = null;
		try {
			vUserInfo = cLogin.login(vLogin);
			if (vUserInfo == null) {
				System.out.println(Global.Locale.LOGIN.ERROR);
			} else {
				System.out.println(vUserInfo.getName() + Global.Locale.LOGIN.WELCOME + sd.format(new Date())
						+ Global.Locale.LOGIN.FIN);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vUserInfo;
	}

}

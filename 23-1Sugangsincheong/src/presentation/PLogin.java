package presentation;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controller.CLogin;
import main.Main;
import valueObject.VLogin;
import valueObject.VUserInfo;

public class PLogin {
	private Scanner scanner;
	private SimpleDateFormat sd = new SimpleDateFormat(global.Locale.LOGIN.TIME_FORMAT);

	public PLogin(Scanner scanner) {
		this.scanner = scanner;
	}

	public VUserInfo run() {
		// get userId&password from user
		System.out.println(global.Locale.LOGIN.ID);
		String userId = scanner.next();
		System.out.println(global.Locale.LOGIN.PASSWORD);
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
				System.out.println(global.Locale.LOGIN.ERROR);
			} else {
				System.out.println(vUserInfo.getName() + global.Locale.LOGIN.WELCOME + sd.format(new Date())
						+ global.Locale.LOGIN.FIN);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vUserInfo;
	}

}

package presentation;

import java.util.Scanner;

import controller.CJoin;
import main.Main;
import valueObject.VAccount;

public class PJoin {

	private Scanner scanner;
	private CJoin cJoin;

	public PJoin(Scanner scanner) {
		this.scanner = scanner;
		this.cJoin = new CJoin();
	}

	public void run() {

		System.out.println(global.Locale.JOIN.START_JOIN);
		VAccount vAccount = new VAccount();

		System.out.println(global.Locale.JOIN.ID);
		String id = scanner.next();
		vAccount.setId(id);

		System.out.println(global.Locale.JOIN.PASSWORD);
		String password = scanner.next();
		String password_encrypt = Main.SHA256(password);
		vAccount.setPassword(password_encrypt);

		System.out.println(global.Locale.JOIN.NAME);
		String name = scanner.next();
		vAccount.setName(name);

		System.out.println(global.Locale.JOIN.ADDRESS);
		String address = scanner.next();
		vAccount.setAddress(address);

		System.out.println(global.Locale.JOIN.DEPARTMENT);
		String department = scanner.next();
		vAccount.setDepartment(department);

		System.out.println(global.Locale.JOIN.EMAIL);
		String eMail = scanner.next();
		vAccount.setEMail(eMail);

		this.cJoin.saveHwewon(vAccount);
		System.out.println(global.Locale.JOIN.FIN_JOIN);
	}
}
package Presentation;

import java.util.Scanner;

import Controller.CJoin;
import Main.Main;
import ValueObject.VAccount;

public class PJoin {

	private Scanner scanner;
	private CJoin cJoin;

	public PJoin(Scanner scanner) {
		this.scanner = scanner;
		this.cJoin = new CJoin();
	}

	public void run() {

		System.out.println("<회원등록>");
		VAccount vAccount = new VAccount();

		System.out.println("아이디:");
		String id = scanner.next();
		vAccount.setId(id);

		System.out.println("비밀번호:");
		String password = scanner.next();
		String password_encrypt = Main.SHA256(password);
		vAccount.setPassword(password_encrypt);

		System.out.println("이름:");
		String name = scanner.next();
		vAccount.setName(name);

		System.out.println("주소:");
		String address = scanner.next();
		vAccount.setAddress(address);

		System.out.println("학과:");
		String department = scanner.next();
		vAccount.setDepartment(department);
		
		System.out.println("이메일:");
		String eMail = scanner.next();
		vAccount.setEMail(eMail);
		
		this.cJoin.saveHwewon(vAccount);
		System.out.println("등록이 완료되었습니다.");
	}
}
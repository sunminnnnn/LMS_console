package Presentation;

import java.util.Scanner;

import ValueObject.VLecture;
import ValueObject.VUserInfo;

public class PInitial {
	private Scanner scanner;
	private PJoin pJoin;
	private PLogin pLogin;
	private PSugangsincheong pSugangsincheong;
	private PMiridamgi pMiridamgi;
	private PLecture pLecture;
	private PUserInfo pUserInfo;

	public PInitial(Scanner scanner) {
		this.scanner = scanner;
		this.pJoin = new PJoin(this.scanner);
		this.pLogin = new PLogin(this.scanner);
		this.pMiridamgi = new PMiridamgi(this.scanner);
		this.pUserInfo = new PUserInfo(this.scanner);
		this.pSugangsincheong = new PSugangsincheong(this.scanner);
	}

	public void show() {
		System.out.println(Global.Locale.INITIAL.MENU_INPUT + Global.Locale.INITIAL.MENU1);
		String command = this.scanner.next();

		if (command.equals("1")) {
			VUserInfo vUserInfo = pLogin.run();
			if (vUserInfo != null) {

				String command2 = "";
				while (!command2.equals("4")) {
					System.out.println(Global.Locale.INITIAL.MENU_INPUT + Global.Locale.INITIAL.MENU2);
					command2 = this.scanner.next();
					switch (command2) {
					case "1":

						VLecture vLecture = pSugangsincheong.run(vUserInfo);

						System.out.println(Global.Locale.INITIAL.MENU_INPUT + Global.Locale.INITIAL.MENU3);
						String command3 = this.scanner.next();

						switch (command3) {
						case "1":
							this.pMiridamgi.save(vUserInfo, vLecture, Global.Locale.FILE.MIRIDAMGI);
							break;
						case "2":
							this.pSugangsincheong.save(vUserInfo, vLecture, Global.Locale.FILE.SINCHEONG);
							break;
						}
						break;
					case "2": // 미리담기목록
						this.pMiridamgi = new PMiridamgi(this.scanner);
						this.pMiridamgi.show(vUserInfo);
						break;
					case "3": // 신청목록
						this.pSugangsincheong.show(vUserInfo);
						break;
					case "4":
						System.out.println(Global.Locale.INITIAL.EXIT);
						System.exit(0);
						break;
					}
				}
			} else {
				System.out.println("(1)아이디 찾기 (2)비밀번호 변경");
				String command4 = this.scanner.next();
				switch (command4) {
				case "1":
					pUserInfo.findID();
					break;
				case "2":
					pUserInfo.findPassword();
					break;
				}
				return;
			}
		} else if (command.equals("2")) {
			this.pJoin.run();
		} else if (command.equals("3")) { // 과목 관리
			this.pLecture = new PLecture(this.scanner);
			this.pLecture.manage();
		} else if (command.equals("4")) {
			this.pUserInfo.show();
		}

	}
}

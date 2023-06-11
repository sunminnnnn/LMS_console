package presentation;

import java.awt.Color;
import java.awt.LayoutManager;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import valueObject.VLecture;
import valueObject.VUserInfo;

public class PInitial extends JPanel {
	private Scanner scanner;
	private PJoin pJoin;
	private PLogin pLogin;
	private PSincheong pSincheong;
	private PMiridamgi pMiridamgi;
	private PSelection pLecture;
	private PUserInfo pUserInfo;
	
	private PSelection pSelection;
	private PSelection pMiridamgiPanel;
	private PSelection pSincheongPanel;

	public PInitial(Scanner scanner) {

		this.scanner = scanner;
		this.pJoin = new PJoin(this.scanner);
		this.pLogin = new PLogin(this.scanner);
		this.pMiridamgi = new PMiridamgi(this.scanner);
		this.pUserInfo = new PUserInfo(this.scanner);
		this.pSincheong = new PSincheong(this.scanner);
		this.pSelection = new PSelection(this.scanner);
		this.pMiridamgiPanel = new PSelection(this.scanner);
		this.pSincheongPanel = new PSelection(this.scanner);

		this.setBackground(Color.white);

		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layoutManager);

		this.add(this.pSelection);
		this.add(this.pMiridamgi);
		this.add(this.pSincheong);
	}

	public void show() {
		System.out.println(global.Locale.INITIAL.MENU_INPUT + global.Locale.INITIAL.MENU1);
		String command = this.scanner.next();

		if (command.equals("1")) {
			VUserInfo vUserInfo = pLogin.run();
			if (vUserInfo != null) {

				String command2 = "";
				while (!command2.equals("4")) {
					System.out.println(global.Locale.INITIAL.MENU_INPUT + global.Locale.INITIAL.MENU2);
					command2 = this.scanner.next();
					switch (command2) {
					case "1":

						VLecture vLecture = pSincheong.run(vUserInfo);

						System.out.println(global.Locale.INITIAL.MENU_INPUT + global.Locale.INITIAL.MENU3);
						String command3 = this.scanner.next();

						switch (command3) {
						case "1":
							this.pMiridamgi.save(vUserInfo, vLecture, global.Locale.FILE.MIRIDAMGI);
							break;
						case "2":
							this.pSincheong.save(vUserInfo, vLecture, global.Locale.FILE.SINCHEONG);
							break;
						}
						break;
					case "2": // 미리담기목록
						this.pMiridamgi = new PMiridamgi(this.scanner);
						this.pMiridamgi.show(vUserInfo);
						break;
					case "3": // 신청목록
						this.pSincheong.show(vUserInfo);
						break;
					case "4":
						this.pUserInfo.changePassword();
						break;
					case "5":
						System.out.println(global.Locale.INITIAL.EXIT);
						System.exit(0);
						break;
					}
				}
			} else {
				System.out.println(global.Locale.INITIAL.MENU4);
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
			this.pLecture = new PSelection(this.scanner);
			this.pLecture.manage();
		} else if (command.equals("4")) {
			this.pUserInfo.show();
		}

	}
}

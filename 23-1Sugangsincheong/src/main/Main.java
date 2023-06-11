package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import presentation.PInitial;
import presentation.PLogin;
import presentation.PLoginDialog;
import presentation.PMainFrame;
import valueObject.VUserInfo;

public class Main {
	private PInitial initial;
	private PLogin pLogin;
	private PLoginDialog loginDialog;
	private VUserInfo vUserInfo;
	int count = 0;

	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getText().equals(global.Locale.OK_LABEL)) {
				try {
					run();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else if (button.getText().equals(global.Locale.LLoginJoinPanel.JOINTITLE)) {
//				PJoin vJoin = new PJoin();
//				vJoin.setVisible(true);
			}
		}
	}

	public Main(Scanner scanner) {
		this.initial = new PInitial(scanner);
	}

	public void initialize() {
		ActionHandler actionHandler = new ActionHandler();
		loginDialog = new PLoginDialog(actionHandler);
		loginDialog.setVisible(true);
	}

	public static String randomPassword() {
		int index = 0;
		char[] charset = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z' };

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			index = (int) (charset.length * Math.random());
			sb.append(charset[index]);
		}
		return sb.toString();
	}

	public static String SHA256(String password) { // one-way hash functions
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes("UTF-8"));
			String hexString = null;

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				hexString += hex;
			}
			return hexString;

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void run() throws FileNotFoundException {
		// this.initial.show();

		this.vUserInfo = loginDialog.login();
		this.loginDialog.dispose();

		if (vUserInfo != null) {
			PMainFrame mainFrame = new PMainFrame(vUserInfo);
			mainFrame.initialize();
		} else {
			count++;
			JOptionPane.showMessageDialog(null,
					global.Locale.LLoginJoinPanel.RE_ID_PW + count + global.Locale.LLoginJoinPanel.RE_ID_PW2,
					global.Locale.LLoginJoinPanel.LOGIN_DIALOG, JOptionPane.DEFAULT_OPTION);
			if (count == 5) {
				JOptionPane.showMessageDialog(null, global.Locale.LLoginJoinPanel.FIND_PW_PLZ,
						global.Locale.LLoginJoinPanel.LOGIN_DIALOG, JOptionPane.DEFAULT_OPTION);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		Main main = new Main(scanner);
		main.initialize();
		//main.run();
		scanner.close();
	}
}

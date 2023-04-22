package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import Main.Main;
import ValueObject.VAccount;
import ValueObject.VEMail;
import ValueObject.VLogin;
import ValueObject.VUserInfo;

public class MAccount {
	private MJoin mJoin;
	private String oldPassword;

	public VUserInfo login(VLogin vLogin) throws FileNotFoundException {
		VUserInfo vUserInfo = null;
		Scanner scanner = new Scanner(new File(Global.Locale.FILE.ACCOUNT));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			if (vLogin.getUserId().equals(tokens[0])) {
				if (vLogin.getPassword().equals(tokens[1])) {
					vUserInfo = new VUserInfo();
					vUserInfo.setId(tokens[0]);
					vUserInfo.setName(tokens[2]);
					vUserInfo.setDepartment(tokens[4]);
					vUserInfo.setEMail(tokens[5]);
					break;
				}
			}
		}
		return vUserInfo;
	}

	public void save(VAccount vAccount) {
		try {
			File file = new File(Global.Locale.FILE.ACCOUNT);
			FileWriter fileWriter = new FileWriter(file, true);
			MJoin mJoin = new MJoin();
			mJoin.save(fileWriter, vAccount);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Vector<VAccount> readAll() {

		Vector<VAccount> indices = new Vector<VAccount>();
		try {
			Scanner scanner = new Scanner(new File(Global.Locale.FILE.ACCOUNT));
			this.mJoin = new MJoin();
			while (mJoin.read(scanner)) {
				VAccount vAccount = new VAccount();
				vAccount.set(this.mJoin);
				indices.add(vAccount);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return indices;
	}

	public VUserInfo getUser(String userId) throws FileNotFoundException {
		return null;
	}

	public void appendAccountFile(String id, String password, String name, String address, String department, String email) throws IOException {
	    FileWriter fw = new FileWriter(Global.Locale.FILE.ACCOUNT, true);
	    fw.write(id + " " + password + " " + name + " " + address + " " + department + " " + email + "\n");
	    fw.close();
	}


	public VUserInfo tempPassWord(String ID, String tempPassword) throws FileNotFoundException {
		/*
		 * 아이디 입력받은 걸로 VAccount 불러서 tempPW로 비밀번호 수정, 그리고 다시 새로운 비밀번호로 수정할 수 있게 함 <- 이
		 * 부분은 다른 메소드로
		 */
		VAccount vAccount = null;
		VUserInfo vUserInfo = null;

		Scanner scanner = new Scanner(new File(Global.Locale.FILE.ACCOUNT));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			if (ID.equals(tokens[0])) {
				// System.out.println(this.read(scanner));

				vAccount = new VAccount();
				vAccount.setId(tokens[0]);
				vAccount.setPassword(tempPassword);
				vAccount.setName(tokens[2]);
				vAccount.setAddress(tokens[3]);
				vAccount.setDepartment(tokens[4]);
				vAccount.setEMail(tokens[5]);

				vUserInfo = new VUserInfo();
				vUserInfo.setId(tokens[0]);
				vUserInfo.setPassword(tempPassword);
				vUserInfo.setName(tokens[2]);
				vUserInfo.setDepartment(tokens[4]);
				vUserInfo.setEMail(tokens[5]);

				try {
					appendAccountFile(tokens[0], Main.SHA256(tempPassword), tokens[2], tokens[3], tokens[4], tokens[5]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		return vUserInfo;
	}

	public void updatePassWord(String ID, String newPassword) throws FileNotFoundException {

	}

	public boolean ExistUserByEmail(VEMail vEMail) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(new File(Global.Locale.FILE.ACCOUNT));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			if (vEMail.getEMail().equals(tokens[5])) {
				return true;
			}
		}
		return false;
	}

	public VUserInfo getUserByEmail(VEMail vEMail) throws FileNotFoundException {
		// TODO Auto-generated method stub
		VUserInfo vUserInfo = null;
		Scanner scanner = new Scanner(new File(Global.Locale.FILE.ACCOUNT));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");
			if (vEMail.getEMail().equals(tokens[5])) {
				vUserInfo = new VUserInfo();
				vUserInfo.setId(tokens[0]);
				vUserInfo.setName(tokens[2]);
				vUserInfo.setDepartment(tokens[4]);
				vUserInfo.setEMail(tokens[5]);
				break;

			}
		}
		return vUserInfo;
	}

	public boolean ExistUserByEmailAndId(VEMail vEMail, String userId) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(Global.Locale.FILE.ACCOUNT));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			if (vEMail.getEMail().equals(tokens[5]) && userId.equals(tokens[0])) {
				return true;
			}
		}
		return false;
	}
}

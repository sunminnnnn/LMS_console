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
	private String id;
	private String password;
	private String name;
	private String address;
	private String department;
	private String eMail;

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

	public void appendAccountFile(String id, String password, String name, String address, String department,
			String email) throws IOException {
		FileWriter fw = new FileWriter(Global.Locale.FILE.ACCOUNT, true);
		fw.write(id + " " + password + " " + name + " " + address + " " + department + " " + email + "\n");
		fw.close();
	}

	public void delete(VUserInfo vUserInfo) {
		Vector<VUserInfo> indicies = readAll(Global.Locale.FILE.ACCOUNT);
		for (int i = 0; i < indicies.size(); i++) {
			if (indicies.get(i).getId().equals(vUserInfo.getId())) {
				indicies.remove(i);
				break;
			}
		}
		writeAll(Global.Locale.FILE.ACCOUNT, indicies);
	}

	public void writeAll(String filename, Vector<VUserInfo> indices) {
		try {
			File file = new File(filename); // Global.Locale.FILE.DATA + 뭐임?
			FileWriter fileWriter = new FileWriter(file);
			for (VUserInfo vUserInfo : indices) {
				this.save(fileWriter, vUserInfo);
			}
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save(VUserInfo vUserInfo) {
		try {
			File file = new File(Global.Locale.FILE.ACCOUNT);
			FileWriter fileWriter = new FileWriter(file, true);
			save(fileWriter, vUserInfo);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save(FileWriter fileWriter, VUserInfo vUserInfo) {
		this.set(vUserInfo);

		try {
			fileWriter.write(id);
			fileWriter.write(" ");
			fileWriter.write(password);
			fileWriter.write(" ");
			fileWriter.write(name);
			fileWriter.write(" ");
			fileWriter.write(address);
			fileWriter.write(" ");
			fileWriter.write(department);
			fileWriter.write(" ");
			fileWriter.write(eMail);
			fileWriter.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void set(VUserInfo vUserInfo) {
		this.id = vUserInfo.getId();
		this.password = vUserInfo.getPassword();
		this.name = vUserInfo.getName();
		this.address = vUserInfo.getAddress();
		this.department = vUserInfo.getDepartment();
		this.eMail = vUserInfo.getEMail();
	}

	public Vector<VUserInfo> readAll(String fileName) {
		Vector<VUserInfo> indices = new Vector<VUserInfo>();
		try {
			Scanner scanner = new Scanner(new File(fileName));

			while (this.read(scanner)) {
				VUserInfo vUserInfo = new VUserInfo();
				vUserInfo.set(this);
				indices.add(vUserInfo);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			return null;
		}
		return indices;
	}

	public boolean read(Scanner scanner) {
		if (scanner.hasNext()) {
			this.id = scanner.next();
			this.password = scanner.next();
			this.name = scanner.next();
			this.address = scanner.next();
			this.department = scanner.next();
			this.eMail = scanner.next();
			return true;
		}
		return false;
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
					delete(vUserInfo);
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

	public VUserInfo getUserByPassword(String nowPassword) throws FileNotFoundException {
		VUserInfo vUserInfo = null;
		Scanner scanner = new Scanner(new File(Global.Locale.FILE.ACCOUNT));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");
			
			if (Main.SHA256(nowPassword).equals(tokens[1])) {
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

	public VUserInfo changePassword(String nowPassword, String tempPassword) throws FileNotFoundException {
		VAccount vAccount = null;
		VUserInfo vUserInfo = null;

		Scanner scanner = new Scanner(new File(Global.Locale.FILE.ACCOUNT));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			if (Main.SHA256(nowPassword).equals(tokens[1])) {
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
					delete(vUserInfo);
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

	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public String getDepartment() {
		return this.department;
	}

	public String getEMail() {
		return this.eMail;
	}


}

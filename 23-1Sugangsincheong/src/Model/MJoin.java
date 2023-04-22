package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import ValueObject.VAccount;

public class MJoin {

	private String id;
	private String password;
	private String name;
	private String address;
	private String department;
	private String eMail;

	public boolean read(Scanner scanner) {
		if (scanner.hasNext()) {
			this.id = scanner.next();
			this.password = scanner.next();
			this.name = scanner.next();
			this.address = scanner.next();
			this.department = scanner.next();
			return true;
		}
		return false;
	}

	public void save(VAccount vAccount) {
		try {
			File file = new File(Global.Locale.FILE.ACCOUNT);
			FileWriter fileWriter = new FileWriter(file, true);
			save(fileWriter, vAccount);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save(FileWriter fileWriter, VAccount vAccount) {
		this.set(vAccount);

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

	public Vector<VAccount> readAll() {

		Vector<VAccount> indices = new Vector<VAccount>();
		try {
			File file = new File(Global.Locale.FILE.ACCOUNT);
			Scanner scanner = new Scanner(file);
			while (this.read(scanner)) {
				VAccount vAccount = new VAccount();
				vAccount.set(this);
				indices.add(vAccount);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return indices;
	}

	public void set(VAccount vAccount) {
		this.id = vAccount.getId();
		this.password = vAccount.getPassword();
		this.name = vAccount.getName();
		this.address = vAccount.getAddress();
		this.department = vAccount.getDepartment();
		this.eMail = vAccount.getEMail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEMail() {
		return eMail;
	}
	
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

}

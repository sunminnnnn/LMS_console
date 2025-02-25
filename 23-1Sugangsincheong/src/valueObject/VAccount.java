package valueObject;

import model.MJoin;

public class VAccount {
	private String id;
	private String password;
	private String name;
	private String address;
	private String department;
	private String eMail;

	public VAccount() {
	}

	public void set(MJoin mJoin) {
		this.id = mJoin.getId();
		this.password = mJoin.getPassword();
		this.name = mJoin.getName();
		this.address = mJoin.getAddress();
		this.department = mJoin.getDepartment();
		this.eMail = mJoin.getEMail();
	}
	
	public void update(VAccount vAccount) {
		this.id = vAccount.getId();
		this.password = vAccount.getPassword();
		this.name = vAccount.getName();
		this.address = vAccount.getAddress();
		this.department = vAccount.getDepartment();
		this.eMail = vAccount.getEMail();
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

	public String getEMail() {
		return eMail;
	}
	
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

}
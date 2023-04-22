package ValueObject;

import Model.MJoin;

public class VUserInfo {
	private String id;
	private String password;
	private String name;
	private String address;
	private String department;
	private String eMail;

	public void set(MJoin mJoin) {
		this.id = mJoin.getId();
		this.password = mJoin.getPassword();
		this.name = mJoin.getName();
		this.address = mJoin.getAddress();
		this.department = mJoin.getDepartment();
		this.eMail = mJoin.getEMail();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

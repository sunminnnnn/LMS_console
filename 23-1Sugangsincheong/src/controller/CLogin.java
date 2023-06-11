package controller;
import java.io.FileNotFoundException;

import model.MAccount;
import valueObject.VLogin;
import valueObject.VUserInfo;

public class CLogin { // 암호화, 성적 평균 내기
	MAccount mAccount;
	
	public CLogin() {
		mAccount = new MAccount();
	}
	
	public VUserInfo login(VLogin vLogin) throws FileNotFoundException {
		VUserInfo vUserInfo = this.mAccount.login(vLogin);

		return vUserInfo;
	}

	public VUserInfo tempPassWord(String ID, String tempPassword) throws FileNotFoundException {
		return this.mAccount.tempPassWord(ID, tempPassword);
	}
	
	public void delete(VUserInfo vUserInfo) {
		this.mAccount.delete(vUserInfo);
	}

	public VUserInfo changePassword(String nowPassword, String tempPassword) throws FileNotFoundException {
		return this.mAccount.changePassword(nowPassword, tempPassword);
	}
}

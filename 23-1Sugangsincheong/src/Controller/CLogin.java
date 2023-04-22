package Controller;
import java.io.FileNotFoundException;

import Model.MAccount;
import ValueObject.VLogin;
import ValueObject.VUserInfo;

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
}

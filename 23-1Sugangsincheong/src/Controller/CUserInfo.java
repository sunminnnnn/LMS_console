package Controller;

import java.io.FileNotFoundException;

import Model.MAccount;
import ValueObject.VEMail;
import ValueObject.VUserInfo;

public class CUserInfo {

	private MAccount mAccount = new MAccount();

	public VUserInfo getUser(String userId) throws FileNotFoundException {
		VUserInfo vuser = mAccount.getUser(userId);

		return vuser;
	}

	public boolean ExistUserByEmail(VEMail vEMail) throws FileNotFoundException {
		return mAccount.ExistUserByEmail(vEMail);
	}

	public VUserInfo getUserByEmail(VEMail vEMail) throws FileNotFoundException {
		return mAccount.getUserByEmail(vEMail);
	}

	public boolean ExistUserByEmailAndId(VEMail vEMail, String userId) throws FileNotFoundException {
		return mAccount.ExistUserByEmailAndId(vEMail, userId);
	}
}

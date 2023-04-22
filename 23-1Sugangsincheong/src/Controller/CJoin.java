package Controller;

import java.util.Vector;

import Model.MJoin;
import ValueObject.VAccount;

public class CJoin {

	private MJoin mJoin;

	public CJoin() {
		this.mJoin = new MJoin();
	}

	public void saveHwewon(VAccount vAccount) {
		this.mJoin.save(vAccount);
	}

	public Vector<VAccount> readAll() {
		Vector<VAccount> vAccountList = this.mJoin.readAll();
		return vAccountList;

	}
}

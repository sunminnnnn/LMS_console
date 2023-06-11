package presentation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import valueObject.VUserInfo;

public class PAccountPanel extends JPanel {

	public PAccountPanel(VUserInfo vUserInfo) {
		JLabel lName = new JLabel(vUserInfo.getName());
		this.add(lName);
		JLabel lGreeting = new JLabel(global.Locale.AccountPanel.INSA_POSTFIX);
		this.add(lGreeting);
		JLabel lLogin = new JLabel(global.Locale.AccountPanel.LOGIN_TIME_PREFIX);
		this.add(lLogin);

		SimpleDateFormat sd = new SimpleDateFormat(global.Locale.AccountPanel.TIME_FORMAT);
		JLabel lTime = new JLabel(sd.format(new Date()));
		this.add(lTime);
		JLabel lDescription = new JLabel(global.Locale.AccountPanel.IPNIDA);
		this.add(lDescription);
	}

}

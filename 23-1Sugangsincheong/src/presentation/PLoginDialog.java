package presentation;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.CLogin;
import main.Main;
import main.Main.ActionHandler;
import valueObject.VLogin;
import valueObject.VUserInfo;

public class PLoginDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel idLabel = new JLabel(global.Locale.LLoginJoinPanel.ID_LABEL);
	private JLabel pwLabel = new JLabel(global.Locale.LLoginJoinPanel.PASSWORD_LABEL);
	private JTextField idText = new JTextField();
	private JPasswordField pwText = new JPasswordField();
	private JButton loginBtn = new JButton(global.Locale.OK_LABEL);
	private JButton cancelBtn = new JButton(global.Locale.CANCEL_LABEL);
	private JButton joinBtn = new JButton(global.Locale.LLoginJoinPanel.JOINTITLE);
	private JButton findPwBtn = new JButton(global.Locale.LLoginJoinPanel.FIND_PW);
	private SimpleDateFormat sd = new SimpleDateFormat(global.Locale.LOGIN.TIME_FORMAT);

	public PLoginDialog(ActionHandler actionHandler) {

		this.setSize(300, 300);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width / 2 - this.getWidth() / 2, 300);
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel p1 = new JPanel();
		p1.setSize(300, 300);
		this.add(p1);

		JPanel p2 = new JPanel();
		p2.setSize(300, 300);
		this.add(p2);

		JPanel p3 = new JPanel();
		p3.setSize(300, 300);
		this.add(p3);

		JPanel p4 = new JPanel();
		p4.setSize(300, 300);
		this.add(p4);

		LayoutManager layoutManager = new FlowLayout();
		this.setLayout(layoutManager);

		this.setTitle("Login");

		p1.add(idLabel);
		p1.add(idText);
		p2.add(pwLabel);
		p2.add(pwText);
		p3.add(loginBtn);
		p3.add(cancelBtn);
		p4.add(joinBtn);
		p4.add(findPwBtn);

		idLabel.setHorizontalAlignment(JLabel.LEFT);

		this.idText.setColumns(10);
		this.pwText.setColumns(10);

		this.getRootPane().setDefaultButton(loginBtn);

		loginBtn.addActionListener(actionHandler);
		joinBtn.addActionListener(actionHandler);
		findPwBtn.addActionListener(actionHandler);

		CLogin cLogin = new CLogin();
	}

	public VUserInfo login() throws FileNotFoundException {
		String userId = this.idText.getText().trim();
		String password = this.pwText.getText().trim();
		String password_decrypt = Main.SHA256(password);

		if (userId.length() == 0 || password.length() == 0) {
			JOptionPane.showMessageDialog(null, global.Locale.LLoginJoinPanel.NULL_ID_PW, global.Locale.NOTICE,
					JOptionPane.DEFAULT_OPTION);
		}
		// generate value object
		VLogin vLogin = new VLogin();
		vLogin.setUserId(userId);
		vLogin.setPassword(password_decrypt);

		// pass VLogin to CLogin
		CLogin cLogin = new CLogin();
		VUserInfo vUserInfo = cLogin.login(vLogin);

		return vUserInfo;
	}

}
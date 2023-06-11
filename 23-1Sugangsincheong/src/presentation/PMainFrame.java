package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import valueObject.VUserInfo;

public class PMainFrame extends JFrame {
	
	private PInitial pInitial;	
	private PAccountPanel accountPanel;


//	public VAccount getVAccount() {
//		return this.vAccount;
//	}
//
//	public void setVAccount(VAccount vAccount) {
//		this.vAccount = vAccount;
//	}

	public PMainFrame(VUserInfo vUserInfo) {
		// attributes
		this.setSize(1000, 600);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width / 2 - this.getWidth() / 2, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//this.vAccount = vAccount;

		LayoutManager layoutManager = new BorderLayout();
		getContentPane().setLayout(layoutManager);
		
		this.accountPanel = new PAccountPanel(vUserInfo);
		getContentPane().add(this.accountPanel, BorderLayout.NORTH);
		
		Scanner scanner = new Scanner(System.in);
		this.pInitial = new PInitial(scanner);
		getContentPane().add(this.pInitial, BorderLayout.CENTER);
	}

	private class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem button = (JMenuItem) e.getSource();
//			if (button.getText().equals(Locale.MainFrame.USER_PAGE)) {
//				myPageDialog.setVisible(true);
//			} else if (button.getText().equals(Locale.MainFrame.MIRIDAMGI_LIST)) {
//				miridamgiDialog.setVisible(true);
//			} else if (button.getText().equals(Locale.MainFrame.SUGANGSINCHEONG_LIST)) {
//
//			}
		}
	}

	public void initialize() {
		this.setVisible(true);
	}
}
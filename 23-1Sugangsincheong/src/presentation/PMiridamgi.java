package presentation;

import java.awt.Color;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JPanel;

import controller.CLecture;
import valueObject.VLecture;
import valueObject.VUserInfo;

public class PMiridamgi extends JPanel {
	private Scanner scanner;
	private CLecture cLecture;

	public PMiridamgi(Scanner scanner) {
		this.scanner = scanner;
		this.cLecture = new CLecture();
		
		this.setBackground(Color.red);
	}

	public void save(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		boolean result = this.check(vUserInfo, vLecture, folder);
		if (result) {
			System.out.println(global.Locale.MIRIDAMGI.ALREADY_EXIST);
		} else {
			if (folder.equals(global.Locale.FILE.MIRIDAMGI)) {
				this.cLecture.save(vUserInfo, vLecture, folder);
				System.out.println(global.Locale.MIRIDAMGI.SUCCESS);
			} else if (folder.equals(global.Locale.FILE.SINCHEONG)) {
				this.cLecture.save(vUserInfo, vLecture, folder);
				this.delete(vUserInfo, vLecture, global.Locale.FILE.MIRIDAMGI);
				System.out.println(global.Locale.SUGANGSINCHEONG.SUCCESS);
			}
		}
	}

	public void delete(String id) {
		this.cLecture.delete(id);
	}

	public void delete(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		this.cLecture.delete(vUserInfo, vLecture, folder);
	}

	private boolean check(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		Vector<VLecture> indicies = this.read(vUserInfo, folder);
		if (indicies == null) {
			return false;
		}
		for (VLecture lecture : indicies) {
			if (lecture.getId().equals(vLecture.getId())) {
				return true;
			}
		}
		return false;
	}

	public Vector<VLecture> read(VUserInfo vUserInfo, String folder) {
		Vector<VLecture> indicies = this.cLecture.readAll(folder + "/" + vUserInfo.getId());
		return indicies;
	}

	public void show(VUserInfo vUserInfo) {
		Vector<VLecture> indicies = this.read(vUserInfo, global.Locale.FILE.MIRIDAMGI);

		for (VLecture vLecture : indicies) {
			System.out.println(vLecture.getId() + " " + vLecture.getName());
		}
		System.out.println(global.Locale.MIRIDAMGI.ASK_SINCHEONG);
		String command = scanner.next();

		if (command.equals("1")) {
			System.out.println(global.Locale.MIRIDAMGI.SINCHEONG);
			String id = this.scanner.next();

			for (VLecture vLecture : indicies) {
				if (vLecture.getId().equals(id)) {
					this.save(vUserInfo, vLecture, global.Locale.FILE.SINCHEONG);
					break;
				}
			}
		} else if (command.equals("2")) {
			System.out.println(global.Locale.MIRIDAMGI.CANCEL);
			String id = this.scanner.next();

			for (VLecture vLecture : indicies) {
				if (vLecture.getId().equals(id)) {
					this.delete(vUserInfo, vLecture, global.Locale.FILE.MIRIDAMGI);
					break;
				}
			}
		}
	}
}

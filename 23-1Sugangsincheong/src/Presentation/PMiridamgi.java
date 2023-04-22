package Presentation;

import java.util.Scanner;
import java.util.Vector;

import Controller.CLecture;
import ValueObject.VLecture;
import ValueObject.VUserInfo;

public class PMiridamgi {
	private Scanner scanner;
	private CLecture cLecture;

	public PMiridamgi(Scanner scanner) {
		this.scanner = scanner;
		this.cLecture = new CLecture();
	}

	public void save(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		boolean result = this.check(vUserInfo, vLecture, folder);
		if (result) {
			System.out.println(Global.Locale.MIRIDAMGI.ALREADY_EXIST);
		} else {
			if (folder.equals(Global.Locale.FILE.MIRIDAMGI)) {
				this.cLecture.save(vUserInfo, vLecture, folder);
				System.out.println(Global.Locale.MIRIDAMGI.SUCCESS);
			} else if (folder.equals(Global.Locale.FILE.SINCHEONG)) {
				this.cLecture.save(vUserInfo, vLecture, folder);
				this.delete(vUserInfo, vLecture, Global.Locale.FILE.MIRIDAMGI);
				System.out.println(Global.Locale.SUGANGSINCHEONG.SUCCESS);
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
		Vector<VLecture> indicies = this.read(vUserInfo, Global.Locale.FILE.MIRIDAMGI);

		for (VLecture vLecture : indicies) {
			System.out.println(vLecture.getId() + " " + vLecture.getName());
		}
		System.out.println(Global.Locale.MIRIDAMGI.ASK_SINCHEONG);
		String command = scanner.next();
		
		if (command.equals("1")) {
			System.out.println(Global.Locale.MIRIDAMGI.SINCHEONG);
			String id = this.scanner.next();

			for (VLecture vLecture : indicies) {
				if (vLecture.getId().equals(id)) {
					this.save(vUserInfo, vLecture, Global.Locale.FILE.SINCHEONG);
					break;
				}
			}
		} else if (command.equals("2")) {
			System.out.println(Global.Locale.MIRIDAMGI.CANCEL);
			String id = this.scanner.next();

			for (VLecture vLecture : indicies) {
				if (vLecture.getId().equals(id)) {
					this.delete(vUserInfo, vLecture, Global.Locale.FILE.MIRIDAMGI);
					break;
				}
			}
		}
	}
}

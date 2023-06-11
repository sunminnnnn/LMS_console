package presentation;

import java.awt.Color;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JPanel;

import controller.CLecture;
import valueObject.VLecture;
import valueObject.VUserInfo;

public class PSincheong extends JPanel {

	private Scanner scanner;
	private PSelection pCampus;
	private PSelection pCollege;
	private PSelection pDepartment;
	private PSelection pLecture;
	private CLecture cLecture;

	public PSincheong(Scanner scanner) {
		this.scanner = scanner;
		this.pCampus = new PSelection(this.scanner);
		this.pCollege = new PSelection(this.scanner);
		this.pDepartment = new PSelection(this.scanner);
		this.pLecture = new PSelection(this.scanner);
		this.cLecture = new CLecture();
		
		this.setBackground(Color.green);
	}

	public VLecture run(VUserInfo vUserInfo) {
		String campusFileName = this.pCampus.findIndexFileName("root" + global.Locale.FILE.TXT,
				global.Locale.SUGANGSINCHEONG.CAMPUS);
		if (campusFileName != null) {
			String collegeFileName = this.pCollege.findIndexFileName(campusFileName + global.Locale.FILE.TXT,
					global.Locale.SUGANGSINCHEONG.COLLEGE);
			if (collegeFileName != null) {
				String departmentFileName = this.pDepartment.findIndexFileName(collegeFileName + global.Locale.FILE.TXT,
						global.Locale.SUGANGSINCHEONG.DEPARTMENT);
				if (departmentFileName != null) {
					VLecture vLecture = this.pLecture.findLecture(departmentFileName + global.Locale.FILE.TXT);
					return vLecture;
				}
			}
		}
		return null;
	}

	public void save(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		boolean result = this.check(vUserInfo, vLecture, folder);
		if (result) {
			System.out.println(global.Locale.SUGANGSINCHEONG.ALREADY_EXIST);
		}
		this.cLecture.save(vUserInfo, vLecture, folder);
	}

	private boolean check(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		Vector<VLecture> indicies = this.read(vUserInfo, folder);
		if (indicies == null) {
			return false;
		}
		for (VLecture lecture : indicies) {
			if (lecture.getId().equals(vLecture.getId()))
				return true;
		}
		return false;
	}

	public Vector<VLecture> read(VUserInfo vUserInfo, String folder) {
		Vector<VLecture> indicies = this.cLecture.readAll(folder + "/" + vUserInfo.getId());
		return indicies;
	}

	public void show(VUserInfo vUserInfo) {
		int allofGrade = 0;
		Vector<VLecture> indicies = this.read(vUserInfo, global.Locale.FILE.SINCHEONG);
		for (VLecture vLecture : indicies) {
			System.out.println(vLecture.getId() + " " + vLecture.getName());
			allofGrade += Integer.parseInt(vLecture.getGrade());
			System.out.println("지금까지 신청한 총 학점은 " + allofGrade + "점입니다.");
		}
		System.out.println(global.Locale.SUGANGSINCHEONG.ASK_CANCEL);
		String command = scanner.next();
		if (command.equals("1")) {
			System.out.println(global.Locale.SUGANGSINCHEONG.CANCEL);
			String id = this.scanner.next();

			for (VLecture vLecture : indicies) {
				if (vLecture.getId().equals(id)) {
					this.delete(vUserInfo, vLecture, global.Locale.FILE.SINCHEONG);
					break;
				}
			}
		}
	}

	public void delete(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		this.cLecture.delete(vUserInfo, vLecture, folder);
	}
}

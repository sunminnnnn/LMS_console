package Presentation;

import java.util.Scanner;
import java.util.Vector;

import Controller.CLecture;
import ValueObject.VLecture;
import ValueObject.VUserInfo;

public class PSugangsincheong {

	private Scanner scanner;
	private PIndex pCampus;
	private PIndex pCollege;
	private PIndex pDepartment;
	private PLecture pLecture;
	private CLecture cLecture;

	public PSugangsincheong(Scanner scanner) {
		this.scanner = scanner;
		this.pCampus = new PIndex(this.scanner);
		this.pCollege = new PIndex(this.scanner);
		this.pDepartment = new PIndex(this.scanner);
		this.pLecture = new PLecture(this.scanner);
		this.cLecture = new CLecture();
	}

	public VLecture run(VUserInfo vUserInfo) {
		String campusFileName = this.pCampus.show("root" + Global.Locale.FILE.TXT,
				Global.Locale.SUGANGSINCHEONG.CAMPUS);
		if (campusFileName != null) {
			String collegeFileName = this.pCollege.show(campusFileName + Global.Locale.FILE.TXT,
					Global.Locale.SUGANGSINCHEONG.COLLEGE);
			if (collegeFileName != null) {
				String departmentFileName = this.pDepartment.show(collegeFileName + Global.Locale.FILE.TXT,
						Global.Locale.SUGANGSINCHEONG.DEPARTMENT);
				if (departmentFileName != null) {
					VLecture oLecture = this.pLecture.show(departmentFileName + Global.Locale.FILE.TXT);
					return oLecture;
				}
			}
		}
		return null;

	}

	public void save(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		boolean result = this.check(vUserInfo, vLecture, folder);
		if (result) {
			System.out.println(Global.Locale.SUGANGSINCHEONG.ALREADY_EXIST);
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
		
	}
}
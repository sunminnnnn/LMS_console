package Presentation;

import java.util.Scanner;
import java.util.Vector;

import Controller.CLecture;
import ValueObject.VLecture;

public class PLecture {

	private Scanner scanner;
	private CLecture cLecture;

	public PLecture(Scanner scanner) {
		this.scanner = scanner;
		this.cLecture = new CLecture();

	}

	public VLecture show(String fileName) {
		System.out.println(Global.Locale.LECTURE.SELECT_LECTURE);

		Vector<VLecture> indices = cLecture.readAll(Global.Locale.FILE.DATA + fileName);

		for (VLecture vLecture : indices) {
			System.out.println(vLecture.getId() + " " + vLecture.getName() + " " + vLecture.getProfessor() + " "
					+ vLecture.getGrade());
		}

		String id = this.scanner.next();

		for (VLecture oLecture : indices) {
			if (oLecture.getId().equals(id)) {
				return oLecture;
			}
		}
		return null;
	}

	public void manage() {
		System.out.println(Global.Locale.LECTURE.LECTURE_MANAGE);
		String command = this.scanner.next();
		switch (command) {
		case "1":
			System.out.println(Global.Locale.LECTURE.SEARCH_LECTURE);
			String id = this.scanner.next().trim();
			VLecture vLecture = this.cLecture.readElement(id);
			if (vLecture != null) {
				getLecture(vLecture);
			} else {
				System.out.println(Global.Locale.LECTURE.NOT_EXIST);
			}
			break;
		case "2":
			update();
			break;
		case "3":
			System.out.println(Global.Locale.LECTURE.DELETE_LECTURE);
			this.cLecture.delete(this.scanner.next());
			System.out.println(Global.Locale.LECTURE.FIN_DELETE);
			break;
		}
	}

	public void update() {
		System.out.println(Global.Locale.LECTURE.UPDATE_LECTURE);
		VLecture vLectureUpdate = this.cLecture.readElement(this.scanner.next());
		if (vLectureUpdate == null) {
			System.out.println(Global.Locale.LECTURE.NOT_EXIST);
		} else {
			getLecture(vLectureUpdate);
			System.out.println(Global.Locale.LECTURE.UPDATE_NAME);
			vLectureUpdate.setName(scanner.next());
			System.out.println(Global.Locale.LECTURE.UPDATE_PROFESSOR);
			vLectureUpdate.setProfessor(scanner.next());
			System.out.println(Global.Locale.LECTURE.UPDATE_GRADE);
			vLectureUpdate.setGrade(scanner.next());
			System.out.println(Global.Locale.LECTURE.UPDATE_TIME);
			vLectureUpdate.setNumber(scanner.next());

			this.cLecture.update(vLectureUpdate);
		}
	}
	
//	public void add() {
//		
//	}

	public void getLecture(VLecture vLecture) {
		System.out.println(vLecture.getId() + " " + vLecture.getName() + " " + vLecture.getProfessor() + " "
				+ vLecture.getGrade() + " " + vLecture.getNumber());
	}
}

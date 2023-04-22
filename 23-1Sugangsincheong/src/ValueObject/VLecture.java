package ValueObject;

import Model.MLecture;

public class VLecture {

	private String id;
	private String name;
	private String professor;
	private String grade;
	private String number;

	public void set(MLecture mLecture) {
		this.id = mLecture.getId();
		this.name = mLecture.getName();
		this.professor = mLecture.getProfessor();
		this.grade = mLecture.getGrade();
		this.number = mLecture.getNumber();
	}

	public void update(VLecture vLecture) {
		this.id = vLecture.getId();
		this.name = vLecture.getName();
		this.professor = vLecture.getProfessor();
		this.grade = vLecture.getGrade();
		this.number = vLecture.getNumber();
	}

	public VLecture() {

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
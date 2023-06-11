package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VLecture;
import valueObject.VUserInfo;

public class MLecture {

	private String id;
	private String name;
	private String professor;
	private String grade;
	private String number;

	public MLecture() {
	}

	/*
	 * issue - 학과랑 강좌랑 구분을 못 하니까 manage가 안 됨 --> " "로 split한 줄의 length가 5 이하면
	 * continue --> 해결!!!
	 */

	public boolean read(Scanner scanner) {
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			if (tokens.length < 5) {
				continue;
			}
			this.id = tokens[0];
			this.name = tokens[1];
			this.professor = tokens[2];
			this.grade = tokens[3];
			this.number = tokens[4];
			return true;
		}
		return false;
	}

	public Vector<VLecture> readAll(String fileName) {
		Vector<VLecture> indices = new Vector<VLecture>();
		try {
			Scanner scanner = new Scanner(new File(fileName));

			while (this.read(scanner)) {
				VLecture vLecture = new VLecture();
				vLecture.set(this);
				indices.add(vLecture);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			return null;
		}
		return indices;
	}

	public Vector<VLecture> getLectures(String fileName) {
		Vector<VLecture> indices = new Vector<VLecture>();
		try {
			Scanner scanner = new Scanner(new File("data/" + fileName));
			while (scanner.hasNext()) {
				VLecture vLecture = new VLecture();
				vLecture.read(scanner);
				indices.add(vLecture);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			return null;
		}
		return indices;
	}

	public VLecture readElement(String id) {
		File path = new File(global.Locale.FILE.DATA);

		File[] fileList = path.listFiles();

		for (File file : fileList) {
			Vector<VLecture> indices = readAll(global.Locale.FILE.DATA + file.getName());
			for (VLecture vLecture : indices) {
				if (vLecture.getId().equals(id)) {
					return vLecture;
				}
			}
		}
		return null;
	}

	public void save(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		try {
			File file = new File(folder + "/" + vUserInfo.getId());
			FileWriter fileWriter = new FileWriter(file, true);
			this.save(fileWriter, vLecture);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save(FileWriter fileWriter, VLecture vLecture) {
		this.set(vLecture);

		try {
			fileWriter.write(id);
			fileWriter.write(" ");
			fileWriter.write(name);
			fileWriter.write(" ");
			fileWriter.write(professor);
			fileWriter.write(" ");
			fileWriter.write(grade);
			fileWriter.write(" ");
			fileWriter.write(number);
			fileWriter.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFilename(String id) {
		File path = new File(global.Locale.FILE.DATA); // "./data/"

		File[] fileList = path.listFiles();

		for (File file : fileList) {
			if (file.isDirectory()) {
				continue;
			}
			Vector<VLecture> indices = readAll(global.Locale.FILE.DATA + file.getName());
			for (VLecture vLecture : indices) {
				if (vLecture.getId().equals(id)) {
					return file.getName();
				}
			}
		}
		return null;
	}

	public void delete(String id) {
		String filename = this.getFilename(id);
		Vector<VLecture> indicies = readAll(global.Locale.FILE.DATA + filename);//
		for (int i = 0; i < indicies.size(); i++) {
			if (indicies.get(i).getId().equals(id)) {
				indicies.remove(i);
				break;
			}
		}
		writeAll(global.Locale.FILE.DATA + filename, indicies);
	}

	public void delete(VUserInfo vUserInfo, VLecture vLectures, String folder) {
		String filename = folder + "/" + vUserInfo.getId();
		Vector<VLecture> indicies = readAll(filename);
		for (int i = 0; i < indicies.size(); i++) {
			if (indicies.get(i).getId().equals(vLectures.getId())) {
				indicies.remove(i);
				break;
			}
		}
		writeAll(filename, indicies);
	}

	public void update(VLecture vLecture) {
		String filename = getFilename(vLecture.getId());
		Vector<VLecture> indicies = readAll(global.Locale.FILE.DATA + filename);//

		for (VLecture lecture : indicies) {
			if (lecture.getId().equals(vLecture.getId())) {
				lecture.update(vLecture);
				break;
			}
		}
		writeAll(global.Locale.FILE.DATA + filename, indicies);
	}

	public void writeAll(String filename, Vector<VLecture> indices) {
		try {
			File file = new File(filename); // Global.Locale.FILE.DATA + 뭐임?
			FileWriter fileWriter = new FileWriter(file);
			// MLecture mLecture = new MLecture();
			for (VLecture lecture : indices) {
				this.save(fileWriter, lecture);
			}
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void set(VLecture vLecture) {
		this.id = vLecture.getId();
		this.name = vLecture.getName();
		this.professor = vLecture.getProfessor();
		this.grade = vLecture.getGrade();
		this.number = vLecture.getNumber();
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

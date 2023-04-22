package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import ValueObject.VLecture;
import ValueObject.VUserInfo;

public class MLecture {

	private String id;
	private String name;
	private String professor;
	private String grade;
	private String number;

	public MLecture() {
	}

	public boolean read(Scanner scanner) {
		if (scanner.hasNext()) {
			this.id = scanner.next();
			this.name = scanner.next();
			this.professor = scanner.next();
			this.grade = scanner.next();
			this.number = scanner.next();
			return true;
		}
		return false;
	}

	public Vector<VLecture> readAll(String fileName) {
		Vector<VLecture> indices = new Vector<VLecture>();
		try {
			Scanner scanner = new Scanner(new File(Global.Locale.FILE.DATA + fileName));

			while (this.read(scanner)) {
				if (id.length() < 4) {
					continue;
				}
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

	public VLecture readElement(String id) {
		File path = new File(Global.Locale.FILE.DATA);

		File[] fileList = path.listFiles();

		for (File file : fileList) {
			Vector<VLecture> indices = readAll(file.getName());
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
		File path = new File(Global.Locale.FILE.DATA); // "./data/"

		File[] fileList = path.listFiles();

		for (File file : fileList) {
			if (file.isDirectory()) {
				continue;
			}
			Vector<VLecture> indices = readAll(file.getName());
			for (VLecture vLecture : indices) {
				if (vLecture.getId().equals(id)) {
					return file.getName();
				}
			}
		}
		return null;
	}

	public void delete(String id) {
		String filename = getFilename(id);
		Vector<VLecture> indicies = readAll(filename);
		for (int i = 0; i < indicies.size(); i++) {
			if (indicies.get(i).getId().equals(id)) {
				indicies.remove(i);
				break;
			}
		}
		writeAll(filename, indicies);
	}

	public void update(VLecture vLecture) {
		String filename = getFilename(vLecture.getId());
		Vector<VLecture> indicies = readAll(filename);

		for (VLecture lecture : indicies) {
			if (lecture.getId().equals(vLecture.getId())) {
				lecture.update(vLecture);
				break;
			}
		}
		writeAll(filename, indicies);
	}

	public void writeAll(String filename, Vector<VLecture> indices) {
		try {
			File file = new File(Global.Locale.FILE.DATA + filename);
			FileWriter fileWriter = new FileWriter(file);
			MLecture mLecture = new MLecture();
			for (VLecture lecture : indices) {
				mLecture.save(fileWriter, lecture);
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

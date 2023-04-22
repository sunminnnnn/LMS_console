package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import ValueObject.VIndex;

public class MIndex {
	private String id;
	private String name;
	private String fileName;

	public MIndex() {
		// TODO Auto-generated constructor stub
	}

	public boolean read(Scanner scanner) {
		if (scanner.hasNext()) {
			this.id = scanner.next();
			this.name = scanner.next();
			this.fileName = scanner.next();
			return true;
		}
		return false;
	}

	public Vector<VIndex> readAll(String fileName) {
		Vector<VIndex> indices = new Vector<VIndex>();
		try {
			File file = new File(Global.Locale.FILE.DATA + fileName);
			Scanner scanner = new Scanner(file);
			while (this.read(scanner)) {
				VIndex vIndex = new VIndex();
				vIndex.set(this);
				indices.add(vIndex);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return indices;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

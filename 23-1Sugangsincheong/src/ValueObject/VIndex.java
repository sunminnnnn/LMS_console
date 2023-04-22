package ValueObject;

import Model.MIndex;

public class VIndex {

	private String id;
	private String name;
	private String fileName;

	public void set(MIndex mIndex) {
		this.id = mIndex.getId();
		this.name = mIndex.getName();
		this.fileName = mIndex.getFileName();
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

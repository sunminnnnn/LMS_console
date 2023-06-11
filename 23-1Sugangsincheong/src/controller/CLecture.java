package controller;

import java.util.Vector;

import model.MLecture;
import valueObject.VLecture;
import valueObject.VUserInfo;

public class CLecture {

	private MLecture mLecture;

	public CLecture() {
		this.mLecture = new MLecture();
	}

	public void save(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		this.mLecture.save(vUserInfo, vLecture, folder);
	}
	
	public Vector<VLecture> getLectures(String fileName) {
		return this.mLecture.getLectures(fileName);
	}

	public Vector<VLecture> readAll(String fileName) {
		Vector<VLecture> indices = this.mLecture.readAll(fileName);
		return indices;
	}

	public VLecture readElement(String id) {
		return this.mLecture.readElement(id);
	}

	public void update(VLecture vLecture) {
		this.mLecture.update(vLecture);
	}

	public void delete(String id) {
		this.mLecture.delete(id);
	}
	
	public void delete(VUserInfo vUserInfo, VLecture vLecture, String folder) {
		this.mLecture.delete(vUserInfo, vLecture, folder);
	}
}
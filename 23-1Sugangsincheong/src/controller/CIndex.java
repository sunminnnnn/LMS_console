package controller;

import java.util.Vector;

import model.MIndex;
import valueObject.VIndex;

public class CIndex {

	private MIndex mIndex;

	public CIndex() {
		this.mIndex = new MIndex();
	}

	public Vector<VIndex> getIndexVector(String fileName) {
		Vector<VIndex> indices = this.mIndex.getIndexVector(fileName);
		return indices;
	}
}

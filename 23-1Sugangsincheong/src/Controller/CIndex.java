package Controller;

import java.util.Vector;

import Model.MIndex;
import ValueObject.VIndex;

public class CIndex {

	private MIndex mIndex;

	public CIndex() {
		this.mIndex = new MIndex();
	}

	public Vector<VIndex> getAll(String fileName) {
		Vector<VIndex> indices = this.mIndex.readAll(fileName);
		return indices;
	}
}

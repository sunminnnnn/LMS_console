package Presentation;

import java.util.Scanner;
import java.util.Vector;

import Controller.CIndex;
import ValueObject.VIndex;

public class PIndex {
	private Scanner scanner;
	private CIndex cIndex;

	public PIndex(Scanner scanner) {
		this.scanner = scanner;
		this.cIndex = new CIndex();
	}

	public String show(String fileName, String message) {
		System.out.println(message + "입력하세요.");
		Vector<VIndex> indices = cIndex.getAll(fileName);
		for (VIndex index : indices) { // 여기서 왜 넘어가지?!
			System.out.println(index.getId() + " " + index.getName());
		}
		String id = this.scanner.next();
		for (VIndex index : indices) {
			if (index.getId().equals(id)) {
				return index.getFileName();
			}
		}
		return null;
	}

}

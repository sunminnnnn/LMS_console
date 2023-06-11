package presentation;

import java.awt.LayoutManager;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.CIndex;
import controller.CLecture;
import global.Locale.lectureHeader;
import valueObject.VIndex;
import valueObject.VLecture;

public class PSelection extends JPanel {
	private int selectedIndexes[];

	private Scanner scanner;
	private CLecture cLecture;
	private CIndex cIndex;

	private PIndexSelectionTable campusTable;
	private PIndexSelectionTable collegeTable;
	private PIndexSelectionTable departmentTable;
	private PLectureSelectionTable lectureTable;

	public PSelection(Scanner scanner) {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);

		this.scanner = scanner;
		this.cLecture = new CLecture();
		this.cIndex = new CIndex();

		this.cLecture = new CLecture();
		this.cIndex = new CIndex();

		JPanel subPanel1 = new JPanel();
		layoutManager = new BoxLayout(subPanel1, BoxLayout.X_AXIS);
		subPanel1.setLayout(layoutManager);

		this.campusTable = new PIndexSelectionTable("캠퍼스");
		this.campusTable.getSelectionModel().addListSelectionListener(new ListSelectionHandler());
		this.campusTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.campusTable);
		subPanel1.add(scrollPane);

		this.collegeTable = new PIndexSelectionTable("대학");
		this.collegeTable.getSelectionModel().addListSelectionListener(new ListSelectionHandler());
		this.collegeTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.collegeTable);
		subPanel1.add(scrollPane);

		this.departmentTable = new PIndexSelectionTable("학과");
		this.departmentTable.getSelectionModel().addListSelectionListener(new ListSelectionHandler());
		this.departmentTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.departmentTable);
		subPanel1.add(scrollPane);

		this.add(subPanel1);

		JPanel subPanel2 = new JPanel();
		layoutManager = new BoxLayout(subPanel2, BoxLayout.Y_AXIS);
		subPanel2.setLayout(layoutManager);

		scrollPane = new JScrollPane();
		this.lectureTable = new PLectureSelectionTable();
		this.lectureTable.getSelectionModel().addListSelectionListener(new ListSelectionHandler());
		this.lectureTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(this.lectureTable);
		subPanel2.add(scrollPane);

		this.add(subPanel2);

		this.updateTable(null);
	}

	private class PIndexSelectionTable extends JTable {

		private static final long serialVersionUID = 1L;
		private DefaultTableModel tableModel;
		private Vector<VIndex> vIndice;
		private CIndex cIndex;

		public PIndexSelectionTable(String title) {
			Vector<String> header = new Vector<String>();
			header.addElement(title);
			this.tableModel = new DefaultTableModel(header, 0);
			this.setModel(this.tableModel);
		}

		public String getFileName(int row) {
			return this.vIndice.get(row).getFileName();
		}

		public String setData(String fileName) {
			this.cIndex = new CIndex();
			this.vIndice = cIndex.getIndexVector(fileName);

			this.tableModel.setNumRows(0);
			this.tableModel.setColumnCount(this.tableModel.getColumnCount());

			for (VIndex vDirectory : vIndice) {
				Vector<String> row = new Vector<String>();
				row.add(vDirectory.getName());
				this.tableModel.addRow(row);
			}
			this.setRowSelectionInterval(0, 0);
			return vIndice.get(0).getFileName();
		}

	}

	private class PLectureSelectionTable extends JTable {
		private static final long serialVersionUID = 1L;
		private DefaultTableModel tableModel;
		private CLecture cLecture;
		private Vector<VLecture> tempLecture;

		public PLectureSelectionTable() {
			Vector<String> header = new Vector<String>();
			for (lectureHeader lectureHeader : lectureHeader.values()) {
				header.addElement(lectureHeader.getText());
			}
			this.tableModel = new DefaultTableModel(header, 0);
			this.setModel(this.tableModel);
		}

		// read data from file/db
		public Vector<VLecture> queryData(String fileName) {
			this.cLecture = new CLecture();
			return cLecture.getLectures(fileName);
		}

		// set data to table
		public void setData(Vector<VLecture> vLectures) {
			this.tableModel.setNumRows(0);
			// this.tableModel.setColumnCount(this.tableModel.getColumnCount());
			this.tempLecture = vLectures;

			for (VLecture vLecture : vLectures) {
				Vector<String> row = new Vector<String>();
				row.add(vLecture.getId());
				row.add(vLecture.getName());
				row.add(vLecture.getProfessor());
				row.add(vLecture.getGrade());
				row.add(vLecture.getNumber());
				this.tableModel.addRow(row);
			}
			if (this.getRowCount() != 0) {
				this.setRowSelectionInterval(0, 0);
			}
		}

		public Vector<VLecture> getSelectedLectures() {
			int[] selectedIndexes = this.getSelectedRows();
			Vector<VLecture> vSelectedLectures = new Vector<VLecture>();
			for (int index = selectedIndexes.length - 1; index >= 0; index--)
				vSelectedLectures.add(this.tempLecture.get(selectedIndexes[index]));

			return vSelectedLectures;
		}

		public Vector<VLecture> removeSelectedLectures() {
			int[] selectedIndexes = this.getSelectedRows();
			Vector<VLecture> vSelectedLectures = new Vector<VLecture>();
			for (int index = selectedIndexes.length - 1; index >= 0; index--) {
				vSelectedLectures.remove(this.tempLecture.get(selectedIndexes[index]));
			}
			return vSelectedLectures;
		}
	}

	private void updateTable(Object object) {
		String fileName = null;

		if (object == null) {
			fileName = "root";
			this.campusTable.setData(fileName + ".txt");

		} else if (object == this.campusTable.getSelectionModel()) {
			selectedIndexes = this.campusTable.getSelectedRows();
			if (selectedIndexes.length > 0) {
				fileName = this.campusTable.getFileName(selectedIndexes[0]);
				this.collegeTable.setData(fileName + ".txt");
			}

		} else if (object == this.collegeTable.getSelectionModel()) {
			selectedIndexes = this.collegeTable.getSelectedRows();
			if (selectedIndexes.length > 0) {
				fileName = this.collegeTable.getFileName(selectedIndexes[0]);
				this.departmentTable.setData(fileName + ".txt");
			}

		} else if (object == this.departmentTable.getSelectionModel()) {
			selectedIndexes = this.departmentTable.getSelectedRows();
			if (selectedIndexes.length > 0) {
				fileName = this.departmentTable.getFileName(selectedIndexes[0]);
				Vector<VLecture> vLectures = this.lectureTable.queryData(fileName + ".txt");
				this.lectureTable.setData(vLectures);
			}

		} else if (object == this.lectureTable) {
			// selectedIndexes = this.lectureTable.getSelectedRows();
		}
	}

	private class ListSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (!event.getValueIsAdjusting()) {
				updateTable(event.getSource());
			}
		}
	}

	public String findIndexFileName(String fileName, String message) {
		System.out.println(message + "입력하세요.");
		Vector<VIndex> indices = cIndex.getIndexVector(fileName);

		for (VIndex index : indices) {
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

	public VLecture findLecture(String fileName) {
		System.out.println(global.Locale.LECTURE.SELECT_LECTURE);

		Vector<VLecture> indices = cLecture.readAll(global.Locale.FILE.DATA + fileName);

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
		System.out.println(global.Locale.LECTURE.LECTURE_MANAGE);
		String command = this.scanner.next();
		switch (command) {
		case "1":
			System.out.println(global.Locale.LECTURE.SEARCH_LECTURE);
			String id = this.scanner.next().trim();
			VLecture vLecture = this.cLecture.readElement(id);
			if (vLecture != null) {
				getLecture(vLecture);
			} else {
				System.out.println(global.Locale.LECTURE.NOT_EXIST);
			}
			break;
		case "2":
			update();
			break;
		case "3":
			System.out.println(global.Locale.LECTURE.DELETE_LECTURE);
			this.cLecture.delete(this.scanner.next());
			System.out.println(global.Locale.LECTURE.FIN_DELETE);
			break;
		}
	}

	public void update() {
		System.out.println(global.Locale.LECTURE.UPDATE_LECTURE);
		VLecture vLectureUpdate = this.cLecture.readElement(this.scanner.next());
		if (vLectureUpdate == null) {
			System.out.println(global.Locale.LECTURE.NOT_EXIST);
		} else {
			getLecture(vLectureUpdate);
			System.out.println(global.Locale.LECTURE.UPDATE_NAME);
			vLectureUpdate.setName(scanner.next());
			System.out.println(global.Locale.LECTURE.UPDATE_PROFESSOR);
			vLectureUpdate.setProfessor(scanner.next());
			System.out.println(global.Locale.LECTURE.UPDATE_GRADE);
			vLectureUpdate.setGrade(scanner.next());
			System.out.println(global.Locale.LECTURE.UPDATE_TIME);
			vLectureUpdate.setNumber(scanner.next());

			this.cLecture.update(vLectureUpdate);
		}
	}

	public void getLecture(VLecture vLecture) {
		System.out.println(vLecture.getId() + " " + vLecture.getName() + " " + vLecture.getProfessor() + " "
				+ vLecture.getGrade() + " " + vLecture.getNumber());
	}
}

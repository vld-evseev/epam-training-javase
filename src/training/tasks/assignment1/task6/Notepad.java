package training.tasks.assignment1.task6;

import java.util.HashMap;
import java.util.Map;

/*
 * ������ ����� ������ ��� �������� �������
 * � ���������� ��� ���� �������� �� ����������, 
 * ��������, �������������� � ���������.
 */

public class Notepad {

	/*
	 * ��������� �������
	 */
	private final Map<Integer, Record> records;

	/*
	 * �������� ���������� ������, ������������� ��������� �������
	 */
	public Notepad() {
		records = new HashMap<>();
	}

	/*
	 * ���������� ������
	 * 
	 * @param note ������
	 */
	public void addRecord(String note) {
		Record record = new Record(note);
		records.put(record.getId(), record);
	}

	/*
	 * �������� ������
	 * 
	 * @param record ������ ��� ��������
	 */
	public void deleteRecord(Record record) {
		records.remove(record.getId());
	}

	/*
	 * �������������� ������ � ������, ���� ����� ������ ����������
	 * 
	 * @param record ������
	 * 
	 * @param newNote ����� ������, ������� ������� ������������
	 */
	public void editRecord(Record record, String newNote) {
		if (findRecordById(record.getId()) == null) {
			return;
		}

		record.setNote(newNote);
	}

	/*
	 * ����� ���������� ������������� ���� ������� � ���������. ���� ���������
	 * ������, ��������� ��������������� ���������
	 */
	public void printAllRecords() {
		if (records.isEmpty()) {
			System.out.println("Notebook is empty");
			return;
		}

		for (Record record : records.values()) {
			System.out.println(record);
		}
	}

	/*
	 * ���������� ������ �� �������� �� ��������������.
	 * 
	 * @return ������. ���� ������ �� ����������, ������������ null
	 * 
	 * @param id ������������� ������
	 */
	public Record findRecordById(int id) {
		return records.get(id);
	}

}

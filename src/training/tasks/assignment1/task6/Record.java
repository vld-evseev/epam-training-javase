package training.tasks.assignment1.task6;

/*
 * ������ ����� ������������ ����� ���������
 * ������, ���������� � ��������
 */

public class Record {

	/*
	 * ����������� ���� ��� �������� �������������� ������
	 */
	private static int nextId = 0;

	/*
	 * ������������� ������� ������
	 */
	private int id;

	/*
	 * ����, ���������� �� �������� ������ � ��������� �������
	 */
	private String note;

	/*
	 * �������� ����� ������; ������������� ��������������
	 * 
	 * @param note ������ � ��������� �������������
	 */
	public Record(String note) {
		this.note = note;
		id = nextId;
		nextId++;
	}

	/*
	 * @param ��������� �������� ���������� �������� ������
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/*
	 * @return ������� ������ � ���� ������
	 */
	public String getNote() {
		return note;
	}

	/*
	 * @param �������������
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "id: " + id + ", note: " + note;
	}
}

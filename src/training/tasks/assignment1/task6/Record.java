package training.tasks.assignment1.task6;

/*
 * Данный класс представляет собой отдельную
 * запись, хранящуюся в блокноте
 */

public class Record {

	/*
	 * Статическое поле для рассчета идентификатора записи
	 */
	private static int nextId = 0;

	/*
	 * Идентификатор текущей записи
	 */
	private int id;

	/*
	 * Поле, отвечающее за хранение записи в строковом формате
	 */
	private String note;

	/*
	 * Создание новой записи; инициализация идентификатора
	 * 
	 * @param note запись в строковом представлении
	 */
	public Record(String note) {
		this.note = note;
		id = nextId;
		nextId++;
	}

	/*
	 * @param изменение текущего строкового значения записи
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/*
	 * @return текущая запись в виде строки
	 */
	public String getNote() {
		return note;
	}

	/*
	 * @param идентификатор
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "id: " + id + ", note: " + note;
	}
}

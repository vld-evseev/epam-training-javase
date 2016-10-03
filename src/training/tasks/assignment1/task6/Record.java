package training.tasks.assignment1.task6;

/*
 * ƒанный класс представл€ет собой отдельную
 * запись, хран€щуюс€ в блокноте
 */

public class Record {

	/*
	 * —татическое поле дл€ рассчета идентификатора записи
	 */
	private static int nextId = 0;

	/*
	 * »дентификатор текущей записи
	 */
	private int id;

	/*
	 * ѕоле, отвечающее за хранение записи в строковом формате
	 */
	private String note;

	/*
	 * —оздание новой записи; инициализаци€ идентификатора
	 * 
	 * @param note запись в строковом представлении
	 */
	public Record(String note) {
		this.note = note;
		id = nextId;
		nextId++;
	}

	/*
	 * @param изменение текущего строкового значени€ записи
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/*
	 * @return текуща€ запись в виде строки
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

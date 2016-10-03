package training.tasks.assignment1.task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>«адание 6</h1> 
 * ƒанный класс служит дл€ демонстрации работы классов
 * Notebook и Record.
 *
 * @author Vlad Evseev
 * @version 1.0
 * @since 2016-10-01
 */

public class Task6 {

	private static final String ADD_KEY = "1";
	private static final String DELETE_KEY = "2";
	private static final String EDIT_KEY = "3";
	private static final String SHOWALL_KEY = "4";
	private static final String QUIT_KEY = "q";

	/*
	 * ƒанный метод отслеживает пользовательский ввод и, в зависимости от
	 * выбранной опции, выполн€ет соответствующий метод класса Notebook.
	 */

	public static void main(String[] args) {

		System.out.println("Use the following keys shortcuts:");
		System.out.println("1 - add record");
		System.out.println("2 - delete record");
		System.out.println("3 - edit record");
		System.out.println("4 - show all records");
		System.out.println("q - exit");

		final Notepad notepad = new Notepad();

		final BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		String line = null;

		try {
			while (!(line = keyboard.readLine()).equals(QUIT_KEY)) {
				switch (line) {
				case ADD_KEY:
					System.out.print("Enter new record: ");
					notepad.addRecord(keyboard.readLine());
					break;
				case DELETE_KEY:
					System.out.print("Enter the record ID to delete: ");
					final int idToDelete = Integer.valueOf(keyboard.readLine());
					final Record recordToDelete = notepad.findRecordById(idToDelete);

					if (recordToDelete != null) {
						notepad.deleteRecord(recordToDelete);
						System.out.println("Record with id " + idToDelete + " deleted");
					} else {
						System.out.println("Record with id " + idToDelete + " not found");
					}

					break;
				case EDIT_KEY:
					System.out.print("Enter the record ID to edit: ");
					final int idToEdit = Integer.valueOf(keyboard.readLine());
					final Record recordToEdit = notepad.findRecordById(idToEdit);

					if (recordToEdit != null) {
						System.out.print("Enter new record: ");
						notepad.editRecord(recordToEdit, keyboard.readLine());
					} else {
						System.out.println("Record with id " + idToEdit + " not found");
					}

					break;
				case SHOWALL_KEY:
					notepad.printAllRecords();
					break;
				default:
					break;
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}

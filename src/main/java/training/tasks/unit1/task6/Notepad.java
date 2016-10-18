package training.tasks.unit1.task6;

import java.util.HashMap;
import java.util.Map;

/**
 * Данный класс служит для хранения записей
 * и выполнения над ними операций по добавлению,
 * удалению, редактированию и просмотру.
 */

public class Notepad {

    /**
     * Коллекция записей
     */
    private final Map<Integer, Record> records;

    /**
     * Создание экземпляра класса, инициализация коллекции записей
     */
    public Notepad() {
        records = new HashMap<>();
    }

    /**
     * Добавление записи
     *
     * @param note строка
     */
    public void addRecord(String note) {
        Record record = new Record(note);
        records.put(record.getId(), record);
    }

    /**
     * Удаление записи
     *
     * @param record запись для удаления
     */
    public void deleteRecord(Record record) {
        records.remove(record.getId());
    }

    /*
     * Редактирование записи в случае, если такая запись существует
     *
     * @param record запись
     *
     * @param newNote новая строка, которая заменит существующую
     */
    public void editRecord(Record record, String newNote) {
        if (findRecordById(record.getId()) == null) {
            return;
        }

        record.setNote(newNote);
    }

    /*
     * Вывод строкового представления всех записей в коллекции. Если коллекция
     * пустая, выводится соответствующее сообщение
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
     * Возвращает запись по значению ее идентификатора.
     *
     * @return запись. Если запись не обнаружена, возвращается null
     *
     * @param id идентификатор записи
     */
    public Record findRecordById(int id) {
        return records.get(id);
    }

}

package training.tasks.unit2.task5;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Group {

    private DisciplineName discipline;
    private boolean integerGradeSystem;

    private final Map<Student, Mark<? extends Number>> studentsGradingMap = new HashMap<>();

    public Group(DisciplineName discipline, Set<Student> students, boolean integerGradeSystem) {
        this.discipline = discipline;
        this.integerGradeSystem = integerGradeSystem;

        for (Student student : students) {
            studentsGradingMap.put(student, null);
        }
    }

    public void addStudent(String firstName, String lastName) {
        studentsGradingMap.put(new Student(firstName, lastName), null);
    }

    public void removeStudent(String firstName, String lastName) {
        Student student = new Student(firstName, lastName);
        if (studentExists(student)) {
            studentsGradingMap.remove(student);
        }
    }

    public Student getStudentByName(String firstName, String lastName) {
        Student studentToFind = new Student(firstName, lastName);

        for (Student student : studentsGradingMap.keySet()) {
            if (student.equals(studentToFind)) {
                return student;
            }
        }

        return null;
    }

    public Map<Student, Mark<? extends Number>> getAllStudents() {
        return new HashMap<>(studentsGradingMap);
    }

    public <T extends Number> void setStudentMark(String firstName, String lastName, T mark) {
        Student student = new Student(firstName, lastName);

        if (studentExists(student)) {
            studentsGradingMap.put(student, checkGradeSystem(mark));
        }
    }

    public Number getMarkByStudentName(String firstName, String lastName) {
        Student student = getStudentByName(firstName, lastName);

        if (studentExists(student)) {
            Mark<? extends Number> mark = studentsGradingMap.get(student);
            return mark != null ? mark.getMark() : 0;
        }

        return -1;
    }

    private <T extends Number> Mark<? super T> checkGradeSystem(Number mark) {
        if (integerGradeSystem) {
            return new Mark<>(mark.intValue());
        }

        return new Mark<>(mark.doubleValue());
    }

    public DisciplineName getDiscipline() {
        return discipline;
    }

    private boolean studentExists(Student student) {
        return studentsGradingMap.containsKey(student);
    }

    public class Mark<T extends Number> {

        private T t;

        public Mark(T t) {
            this.t = t;
        }

        public T getMark() {
            return t;
        }
    }
}

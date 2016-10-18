package training.tasks.unit2.task5;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The class represents a group of students, depending of the discipline
 * Each discipline follows it's own grading system - integer or real
 */
public class Group {

    /**
     * The discipline name, defined in DisciplineName enumeration class
     */
    private DisciplineName discipline;

    /**
     * Represents if the group follows integer grading system
     */
    private boolean integerGradeSystem;

    /**
     * Table of students associated by their marks
     */
    private final Map<Student, Mark<? extends Number>> studentsGradingMap = new HashMap<>();

    /**
     * Creates a new group of students
     *
     * @param discipline         defines a name of discipline of the group
     * @param students           defines a members of the group
     * @param integerGradeSystem defines if the group follows an integer grading system
     */
    public Group(DisciplineName discipline, Set<Student> students, boolean integerGradeSystem) {
        this.discipline = discipline;
        this.integerGradeSystem = integerGradeSystem;

        for (Student student : students) {
            studentsGradingMap.put(student, null);
        }
    }

    /**
     * Adds a new student to the group
     *
     * @param firstName first name of student
     * @param lastName  last name of student
     */
    public void addStudent(String firstName, String lastName) {
        studentsGradingMap.put(new Student(firstName, lastName), null);
    }

    /**
     * Removes student from the group by his name
     *
     * @param firstName first name of student
     * @param lastName  last name of student
     */
    public void removeStudent(String firstName, String lastName) {
        Student student = new Student(firstName, lastName);
        if (studentExists(student)) {
            studentsGradingMap.remove(student);
        }
    }

    /**
     * Returns a student by name if he is a part of the group
     *
     * @param firstName first name of student
     * @param lastName  last name of student
     * @return a student if he exists in the group, null otherwise
     */
    public Student getStudentByName(String firstName, String lastName) {
        Student studentToFind = new Student(firstName, lastName);

        for (Student student : studentsGradingMap.keySet()) {
            if (student.equals(studentToFind)) {
                return student;
            }
        }

        return null;
    }

    /**
     * @return a map of all in-group students and their marks
     */
    public Map<Student, Mark<? extends Number>> getAllStudents() {
        return new HashMap<>(studentsGradingMap);
    }

    /**
     * Sets a mark to student by his name if the student exists in the group
     *
     * @param firstName first name of student
     * @param lastName  last name of student
     * @param mark      a mark that should be integer or real number
     */
    public <T extends Number> void setStudentMark(String firstName, String lastName, T mark) {
        Student student = new Student(firstName, lastName);

        if (studentExists(student)) {
            studentsGradingMap.put(student, checkGradeSystem(mark));
        }
    }

    /**
     * @param firstName first name of student
     * @param lastName  last name of student
     * @return a mark in integer or real representation depending of the grading system of discipline
     */
    public Number getMarkByStudentName(String firstName, String lastName) {
        Student student = getStudentByName(firstName, lastName);

        if (studentExists(student)) {
            Mark<? extends Number> mark = studentsGradingMap.get(student);
            return mark != null ? mark.getMark() : 0;
        }

        return -1;
    }

    /**
     * @return discipline of the group
     */
    public DisciplineName getDiscipline() {
        return discipline;
    }

    private <T extends Number> Mark<? super T> checkGradeSystem(Number mark) {
        if (integerGradeSystem) {
            return new Mark<>(mark.intValue());
        }

        return new Mark<>(mark.doubleValue());
    }

    private boolean studentExists(Student student) {
        return studentsGradingMap.containsKey(student);
    }

    /**
     * Represents a mark that depends of grading system of the group
     *
     * @param <T> number in integer or real representation
     */
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

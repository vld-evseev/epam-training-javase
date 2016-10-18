package training.tasks.unit2.task5;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void addStudent() throws Exception {
        Group group = createMathematicsGroup();
        group.addStudent("Albert", "Einstein");

        Student albertEinstein = group.getStudentByName("Albert", "Einstein");
        assertNotNull(albertEinstein);
    }

    @Test
    public void removeStudent() throws Exception {
        Group group = createMathematicsGroup();
        group.removeStudent("John", "Doe");

        Student johnDoe = new Student("John", "Doe");
        Student frankSmith = new Student("Frank", "Smith");

        assertFalse(group.getAllStudents().containsKey(johnDoe));
        assertTrue(group.getAllStudents().containsKey(frankSmith));
    }

    @Test
    public void getStudentByName() throws Exception {
        Group group = createMathematicsGroup();

        Student existingStudent = group.getStudentByName("John", "Doe");
        assertNotNull(existingStudent);

        Student nonExistingStudent = group.getStudentByName("Vladimir", "Putin");
        assertNull(nonExistingStudent);
    }

    @Test
    public void setStudentMarkGradeSystemReal() throws Exception {
        Group group = createMathematicsGroup();
        group.setStudentMark("Michael", "Brown", 5);
        group.setStudentMark("Frank", "Smith", 4.5);
        group.setStudentMark("Adam", "Williams", 3.4);

        printStudentsTable(group);

        assertEquals(group.getMarkByStudentName("Michael", "Brown"), 5.0);
        assertEquals(group.getMarkByStudentName("Frank", "Smith"), 4.5);
        assertEquals(group.getMarkByStudentName("Marylin", "Manson"), -1);
    }

    @Test
    public void setStudentMarkGradeSystemInteger() throws Exception {
        Group group = createPhilosophyGroup();
        group.setStudentMark("John", "Doe", 4);
        group.setStudentMark("Rodger", "Davis", 5);
        group.setStudentMark("Michael", "Brown", 3);

        printStudentsTable(group);

        assertEquals(group.getMarkByStudentName("Michael", "Brown"), 3);
        assertEquals(group.getMarkByStudentName("John", "Doe"), 4);
        assertEquals(group.getMarkByStudentName("Marylin", "Manson"), -1);
    }

    @Test
    public void getDiscipline() throws Exception {
        Group group = createMathematicsGroup();
        assertEquals(group.getDiscipline(), DisciplineName.MATHEMATICAL_ANALYSIS);
    }

    @Test
    public void getAllStudents() throws Exception {
        Group group = createMathematicsGroup();
        int groupSize = group.getAllStudents().size();
        assertEquals(groupSize, 5);
    }

    @Test
    public void compareStudentMarks() {
        Group mathematicsGroup = createMathematicsGroup();
        Group physicsGroup = createPhysicsGroup();
        Group philosophyGroup = createPhilosophyGroup();

        mathematicsGroup.setStudentMark("John", "Doe", 5);
        mathematicsGroup.setStudentMark("Frank", "Smith", 4.3);

        philosophyGroup.setStudentMark("John", "Doe", 3);
        philosophyGroup.setStudentMark("Rodger", "Davis", 4);

        physicsGroup.setStudentMark("Frank", "Smith", 3.8);
        physicsGroup.setStudentMark("Rodger", "Davis", 4.7);

        List<Group> groupList = new ArrayList<>();
        groupList.add(mathematicsGroup);
        groupList.add(physicsGroup);
        groupList.add(philosophyGroup);

        printStudentMarks(groupList, "John", "Doe");
        printStudentMarks(groupList, "Rodger", "Davis");
        printStudentMarks(groupList, "Frank", "Smith");

    }

    private void printStudentMarks(List<Group> groupList, String firstName, String lastName) {
        System.out.println(lastName + ", " + firstName + ":");

        for (Group group : groupList) {
            Number mark = group.getMarkByStudentName(firstName, lastName);
            if (mark.intValue() >= 0) {
                System.out.println(group.getDiscipline() + " : " + mark);
            }
        }

        System.out.println();
    }


    private void printStudentsTable(Group group) {
        Map<Student, Group.Mark<? extends Number>> allStudents = group.getAllStudents();
        Set<Map.Entry<Student, Group.Mark<? extends Number>>> entries = allStudents.entrySet();

        System.out.println(group.getDiscipline());
        for (Map.Entry<Student, Group.Mark<? extends Number>> entry : entries) {
            Student student = entry.getKey();
            Group.Mark<? extends Number> mark = entry.getValue();

            System.out.println(student + ": " + (mark != null ? mark.getMark() : 0));
        }

        System.out.println();
    }

    private Group createMathematicsGroup() {
        Set<Student> students = new HashSet<>();

        students.add(new Student("John", "Doe"));
        students.add(new Student("Frank", "Smith"));
        students.add(new Student("Adam", "Williams"));
        students.add(new Student("Michael", "Brown"));
        students.add(new Student("Whitney", "Jones"));

        return new Group(DisciplineName.MATHEMATICAL_ANALYSIS, students, false);
    }

    private Group createPhilosophyGroup() {
        Set<Student> students = new HashSet<>();

        students.add(new Student("John", "Doe"));
        students.add(new Student("Jack", "White"));
        students.add(new Student("Rodger", "Davis"));
        students.add(new Student("Michael", "Brown"));
        students.add(new Student("Quentin", "Tarantino"));

        return new Group(DisciplineName.PHILOSOPHY, students, true);
    }

    private Group createPhysicsGroup() {
        Set<Student> students = new HashSet<>();

        students.add(new Student("John", "Doe"));
        students.add(new Student("Adam", "Williams"));
        students.add(new Student("Rodger", "Davis"));
        students.add(new Student("Frank", "Smith"));
        students.add(new Student("Albert", "Einstein"));

        return new Group(DisciplineName.PHYSICS, students, false);
    }

}
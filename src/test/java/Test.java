import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.student.student__.Student;  // Импорт класса Student

class StudentTest {

    @Test
    void testDefaultConstructor() {
        Student student = new Student();
        assertEquals("Неизвестно", student.getName());
        assertEquals(18, student.getAge());
        assertEquals(0, student.getStudentId());
        assertEquals(0.0, student.getAverageGrade());
        assertEquals("Не указана", student.getGroup());
        assertEquals(1, student.getCourse());
    }

    @Test
    void testFullConstructor() {
        Student student = new Student("Иванов Иван", 20, 123456, 4.5, "ИВТ-21", 2);
        assertEquals("Иванов Иван", student.getName());
        assertEquals(20, student.getAge());
        assertEquals(123456, student.getStudentId());
        assertEquals(4.5, student.getAverageGrade());
        assertEquals("ИВТ-21", student.getGroup());
        assertEquals(2, student.getCourse());
    }

    @Test
    void testSetName() {
        Student student = new Student();
        student.setName("Петров Петр");
        assertEquals("Петров Петр", student.getName());
    }

    @Test
    void testSetNameInvalid() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setName(""));
    }

    @Test
    void testSetNameInvalidNull() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setName(null));
    }

    @Test
    void testSetAge() {
        Student student = new Student();
        student.setAge(25);
        assertEquals(25, student.getAge());
    }

    @Test
    void testSetAgeInvalidLow() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setAge(15));
    }

    @Test
    void testSetAgeInvalidHigh() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setAge(101));
    }

    @Test
    void testSetStudentId() {
        Student student = new Student();
        student.setStudentId(987654);
        assertEquals(987654, student.getStudentId());
    }

    @Test
    void testSetStudentIdInvalid() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setStudentId(-1));
    }

    @Test
    void testSetAverageGrade() {
        Student student = new Student();
        student.setAverageGrade(3.7);
        assertEquals(3.7, student.getAverageGrade());
    }

    @Test
    void testSetAverageGradeInvalidLow() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setAverageGrade(-0.1));
    }

    @Test
    void testSetAverageGradeInvalidHigh() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setAverageGrade(5.1));
    }

    @Test
    void testSetGroup() {
        Student student = new Student();
        student.setGroup("АБВ-22");
        assertEquals("АБВ-22", student.getGroup());
    }

    @Test
    void testSetGroupInvalid() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setGroup(""));
    }

    @Test
    void testSetGroupInvalidNull() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setGroup(null));
    }

    @Test
    void testSetCourse() {
        Student student = new Student();
        student.setCourse(3);
        assertEquals(3, student.getCourse());
    }

    @Test
    void testSetCourseInvalidLow() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setCourse(0));
    }

    @Test
    void testSetCourseInvalidHigh() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> student.setCourse(7));
    }
    }
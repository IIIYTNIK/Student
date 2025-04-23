package com.student.student__;

public class Student {
    private String name;         // Имя
    private int age;             // Возраст
    private int studentId;       // ID студента
    private double averageGrade; // Средний балл
    private String group;        // Учебная группа
    private int course;          // Курс

    /// Конструктор с параметрами (использует сеттеры)
    /// name типа String, age типа unt, ID типа int, grade типа double, group типа string, course типа int
    public Student(String name, int age, int studentId, double averageGrade, String group, int course) {
        setName(name);
        setAge(age);
        setStudentId(studentId);
        setAverageGrade(averageGrade);
        setGroup(group);
        setCourse(course);
    }

    /// Конструктор по умолчанию
    public Student() {
        this("Неизвестно", 18, 000000, 0.0, "Не указана", 1);
    }


    // Геттеры
    public  String getName() { return name; }
    public int getAge() { return age; }
    public int getStudentId() { return studentId; }
    public double getAverageGrade() { return averageGrade; }
    public String getGroup() { return group; }
    public int getCourse() { return course; }

    // Сеттеры
    /// Сеттер ФИО. Принимает name типа string. Не может быть пустым
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {    //trim убирает пробелы
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name.trim();
    }

    /// Сеттер Возраста. Принимает age типа int. Допустимы только целые числа от 16 до 100
    public void setAge(int age) {
        if (age < 16 || age > 100) {
            throw new IllegalArgumentException("Возраст должен быть от 16 до 100");
        }
        this.age = age;
    }

    /// Сеттер ID студента. Принимает studentId типа int. Не может быть пустым
    public void setStudentId(int studentId) {
        if (studentId < -1 || studentId > 1000000) {
            throw new IllegalArgumentException("ID студента не может быть пустым");
        }
        this.studentId = studentId;
    }

    /// Сеттер среднего балла студента. Принимает averageGrade типа double. Принимает дробные числа от 0 до 5
    public void setAverageGrade(double averageGrade) {
        if (averageGrade < 0.0 || averageGrade > 5.0) {
            throw new IllegalArgumentException("Средний балл должен быть от 0.0 до 5.0");
        }
        this.averageGrade = averageGrade;
    }

    /// Сеттер группы студента. Принимает group типа String. Не может быть пустым
    public void setGroup(String group) {
        if (group == null || group.trim().isEmpty()) { //trim убирает пробелы
            throw new IllegalArgumentException("Группа не может быть пустой");
        }
        this.group = group.trim();
    }

    /// Сеттер курса. Принимает course типа int. Принимает только целые числа от 1 до 6
    public void setCourse(int course) {
        if (course < 1 || course > 6) { // Предположим, что курс от 1 до 6
            throw new IllegalArgumentException("Курс должен быть от 1 до 6");
        }
        this.course = course;
    }

    //toString()
    @Override
    public String toString() {
        return String.format(
                "Студент: %s | Возраст: %d | ID: %d | Группа: %s | Курс: %d | Средний балл: %.1f",
                name, age, studentId, group, course, averageGrade
        );
    }
}
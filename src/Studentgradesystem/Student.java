package Studentgradesystem;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student {
    private String name;
    private String rollNumber;
    private Map<String, Integer> marks;

    public Student(String name, String rollNumber, Map<String, Integer> marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = new HashMap<>(marks);
    }

    public double calculatePercentage() {
        if (marks.isEmpty()) {
            return 0.0;
        }
        int totalMarks = marks.values().stream().mapToInt(Integer::intValue).sum();
        int totalSubjects = marks.size();
        return (double) totalMarks / (totalSubjects * 100) * 100;
    }

    public String calculateGrade() {
        double percentage = calculatePercentage();
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public void displayStudentInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Subject Marks:");
        marks.forEach((subject, mark) -> System.out.println(subject + ": " + mark));
        System.out.printf("Overall Percentage: %.2f%%\n", calculatePercentage());
        System.out.println("Grade: " + calculateGrade());
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public String getRollNumber() {
        return rollNumber;
    }
}

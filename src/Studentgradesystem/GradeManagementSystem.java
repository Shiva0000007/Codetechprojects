package Studentgradesystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeManagementSystem {
    private Map<String, Student> students;
    private Scanner scanner;

    public GradeManagementSystem() {
        this.students = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.println("Enter student name:");
        String name = scanner.nextLine();

        System.out.println("Enter student roll number:");
        String rollNumber = scanner.nextLine();

        Map<String, Integer> marks = new HashMap<>();
        System.out.println("Enter marks for each subject:");
        System.out.println("(Enter 'done' when finished)");
        while (true) {
            System.out.print("Subject: ");
            String subject = scanner.nextLine();
            if (subject.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Marks: ");
            int mark = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            marks.put(subject, mark);
        }

        Student newStudent = new Student(name, rollNumber, marks);
        students.put(rollNumber, newStudent);
        System.out.println("Student added successfully.");
    }

    public void updateStudent() {
        System.out.println("Enter the roll number of the student to update:");
        String rollNumber = scanner.nextLine();

        if (students.containsKey(rollNumber)) {
            Student existingStudent = students.get(rollNumber);
            System.out.println("Enter new marks for each subject:");
            System.out.println("(Enter 'done' when finished)");
            while (true) {
                System.out.print("Subject: ");
                String subject = scanner.nextLine();
                if (subject.equalsIgnoreCase("done")) {
                    break;
                }
                System.out.print("Marks: ");
                int mark = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                existingStudent.getMarks().put(subject, mark);
            }
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent() {
        System.out.println("Enter the roll number of the student to delete:");
        String rollNumber = scanner.nextLine();

        if (students.containsKey(rollNumber)) {
            students.remove(rollNumber);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void displayAllStudents() {
        students.values().forEach(Student::displayStudentInfo);
    }

    public static void main(String[] args) {
        GradeManagementSystem system = new GradeManagementSystem();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = system.scanner.nextInt();
            system.scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    system.addStudent();
                    break;
                case 2:
                    system.updateStudent();
                    break;
                case 3:
                    system.deleteStudent();
                    break;
                case 4:
                    system.displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

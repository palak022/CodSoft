package Task_5;

import java.io.*;
import java.util.*;

public class StudentManagementSystem {

    // ---------- STUDENT CLASS ----------
    static class Student {
        String name;
        int rollNumber;
        String grade;
        String course;

        Student(String name, int rollNumber, String grade, String course) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
            this.course = course;
        }

        @Override
        public String toString() {
            return rollNumber + "," + name + "," + grade + "," + course;
        }
    }

    // ---------- DATA STORAGE ----------
    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.txt";
    static Scanner sc = new Scanner(System.in);

    // ---------- MAIN ----------
    public static void main(String[] args) {

        loadFromFile();

        System.out.println("====================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM");
        System.out.println("====================================");

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> removeStudent();
                case 4 -> searchStudent();
                case 5 -> displayStudents();
                case 6 -> {
                    saveToFile();
                    System.out.println("Data saved. Exiting system.");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ---------- ADD STUDENT ----------
    static void addStudent() {
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        if (findStudent(roll) != null) {
            System.out.println("Student with this roll number already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        if (name.isEmpty() || grade.isEmpty() || course.isEmpty()) {
            System.out.println("Error: Fields cannot be empty.");
            return;
        }

        students.add(new Student(name, roll, grade, course));
        System.out.println("Student added successfully.");
    }

    // ---------- EDIT STUDENT ----------
    static void editStudent() {
        System.out.print("Enter Roll Number to edit: ");
        int roll = sc.nextInt();
        sc.nextLine();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        s.name = sc.nextLine();

        System.out.print("Enter New Grade: ");
        s.grade = sc.nextLine();

        System.out.print("Enter New Course: ");
        s.course = sc.nextLine();

        System.out.println("Student details updated.");
    }

    // ---------- REMOVE STUDENT ----------
    static void removeStudent() {
        System.out.print("Enter Roll Number to remove: ");
        int roll = sc.nextInt();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(s);
        System.out.println("Student removed successfully.");
    }

    // ---------- SEARCH STUDENT ----------
    static void searchStudent() {
        System.out.print("Enter Roll Number to search: ");
        int roll = sc.nextInt();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("\nStudent Details:");
            System.out.println("Roll Number: " + s.rollNumber);
            System.out.println("Name       : " + s.name);
            System.out.println("Grade      : " + s.grade);
            System.out.println("Course     : " + s.course);
        }
    }

    // ---------- DISPLAY ALL ----------
    static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\nROLL | NAME | GRADE | COURSE");
        System.out.println("--------------------------------");
        for (Student s : students) {
            System.out.println(
                s.rollNumber + " | " +
                s.name + " | " +
                s.grade + " | " +
                s.course
            );
        }
    }

    // ---------- FIND STUDENT ----------
    static Student findStudent(int roll) {
        for (Student s : students) {
            if (s.rollNumber == roll)
                return s;
        }
        return null;
    }

    // ---------- FILE SAVE ----------
    static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s);
            }
        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }

    // ---------- FILE LOAD ----------
    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(
                        data[1],
                        Integer.parseInt(data[0]),
                        data[2],
                        data[3]
                ));
            }
        } catch (Exception e) {
            System.out.println("Error loading data.");
        }
    }
}

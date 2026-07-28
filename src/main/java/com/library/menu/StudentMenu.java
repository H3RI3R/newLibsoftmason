package com.library.menu;

import com.library.entity.Student;
import com.library.service.StudentService;

import java.util.Scanner;

public class StudentMenu {

    static Scanner scanner = new Scanner(System.in);

    static StudentService studentService = new StudentService();


    // ==========================================
    // SHOW STUDENT MANAGEMENT MENU
    // ==========================================

    public  static void showMenu() {

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT =====");

            System.out.println("1. Register Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Back");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerStudent();
                    break;

                case 2:
                    studentService.viewAllStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Going back...");
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please enter 1 to 6."
                    );
            }

        } while (choice != 6);
    }


    // ==========================================
    // 1. REGISTER STUDENT
    // ==========================================

    public static void registerStudent() {

        Student student = new Student();


        // Student ID
//        student.studentId =
//                readInt("Enter Student ID: ");


        // Student Name
        student.studentName =
                readNonEmpty("Enter Student Name: ");


        // Department
        student.department =
                readNonEmpty("Enter Department: ");


        // Year validation
        while (true) {

            student.year =
                    readInt("Enter Year (1-4): ");

            if (student.year >= 1 &&
                    student.year <= 4) {

                break;

            } else {

                System.out.println(
                        "Invalid year! Please enter 1, 2, 3 or 4."
                );
            }
        }


        // Section
        student.section =
                readNonEmpty("Enter Section: ");


        // Roll Number
        student.rollNumber =
                readNonEmpty("Enter Roll Number: ");


        // Phone validation
        while (true) {

            System.out.print("Enter Phone: ");

            student.phone =
                    scanner.nextLine().trim();

            if (student.phone.matches("\\d{10}")) {

                break;

            } else {

                System.out.println(
                        "Invalid phone number!"
                );

                System.out.println(
                        "Phone number must contain exactly 10 digits."
                );
            }
        }


        // Email validation
        while (true) {

            System.out.print("Enter Email: ");

            student.email =
                    scanner.nextLine().trim();

            if (isValidEmail(student.email)) {

                break;

            } else {

                System.out.println(
                        "Invalid email! Example: name@gmail.com"
                );
            }
        }


        // Address
        student.address =
                readNonEmpty("Enter Address: ");


        // Send student to service
        studentService.addStudent(student);
    }


    // ==========================================
    // 3. SEARCH STUDENT
    // ==========================================

    public static void searchStudent() {

        int studentId =
                readInt(
                        "Enter Student ID to search: "
                );

        Student student =
                studentService.searchStudent(
                        studentId
                );

        if (student != null) {

            System.out.println(
                    "\n===== STUDENT FOUND ====="
            );

            displayStudent(student);

        } else {

            System.out.println(
                    "Student not found!"
            );
        }
    }


    // ==========================================
    // 4. UPDATE STUDENT
    // ==========================================

    public static void updateStudent() {

        int studentId =
                readInt(
                        "Enter Student ID to update: "
                );


        // Check whether student exists first
        Student existingStudent =
                studentService.searchStudent(
                        studentId
                );

        if (existingStudent == null) {

            System.out.println(
                    "Student not found!"
            );

            return;
        }


        // New phone validation
        String newPhone;

        while (true) {

            System.out.print(
                    "Enter New Phone: "
            );

            newPhone =
                    scanner.nextLine().trim();

            if (newPhone.matches("\\d{10}")) {

                break;

            } else {

                System.out.println(
                        "Invalid phone number!"
                );

                System.out.println(
                        "Phone number must contain exactly 10 digits."
                );
            }
        }


        // New email validation
        String newEmail;

        while (true) {

            System.out.print(
                    "Enter New Email: "
            );

            newEmail =
                    scanner.nextLine().trim();

            if (isValidEmail(newEmail)) {

                break;

            } else {

                System.out.println(
                        "Invalid email! Example: name@gmail.com"
                );
            }
        }


        boolean updated =
                studentService.updateStudent(
                        studentId,
                        newPhone,
                        newEmail
                );

        if (updated) {

            System.out.println(
                    "Student updated successfully!"
            );

        } else {

            System.out.println(
                    "Student update failed!"
            );
        }
    }


    // ==========================================
    // 5. DELETE STUDENT
    // ==========================================

    public static void deleteStudent() {

        int studentId =
                readInt(
                        "Enter Student ID to delete: "
                );

        boolean deleted =
                studentService.deleteStudent(
                        studentId
                );

        if (deleted) {

            System.out.println(
                    "Student deleted successfully!"
            );

        } else {

            System.out.println(
                    "Student not found!"
            );
        }
    }


    // ==========================================
    // HELPER METHOD - READ INTEGER
    // ==========================================

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input! Please enter numbers only."
                );
            }
        }
    }


    // ==========================================
    // HELPER METHOD - EMPTY INPUT CHECK
    // ==========================================

    private static String readNonEmpty(
            String message
    ) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {

                return input;

            }

            System.out.println(
                    "This field cannot be empty!"
            );
        }
    }


    // ==========================================
    // HELPER METHOD - EMAIL VALIDATION
    // ==========================================

    private static boolean isValidEmail(
            String email
    ) {

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }


    // ==========================================
    // HELPER METHOD - DISPLAY STUDENT
    // ==========================================

    private static void displayStudent(
            Student student
    ) {

//        System.out.println(
//                "Student ID   : "
//                        + student.studentId
//        );

        System.out.println(
                "Student Name : "
                        + student.studentName
        );

        System.out.println(
                "Department   : "
                        + student.department
        );

        System.out.println(
                "Year         : "
                        + student.year
        );

        System.out.println(
                "Section      : "
                        + student.section
        );

        System.out.println(
                "Roll Number  : "
                        + student.rollNumber
        );

        System.out.println(
                "Phone        : "
                        + student.phone
        );

        System.out.println(
                "Email        : "
                        + student.email
        );

        System.out.println(
                "Address      : "
                        + student.address
        );
    }
}
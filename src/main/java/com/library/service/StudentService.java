package com.library.service;

import com.library.dao.StudentDAO;
import com.library.entity.Student;

import java.util.List;

public class StudentService {

    // DAO object used to communicate with MySQL
    StudentDAO studentDAO =
            new StudentDAO();


    // ==========================================
    // 1. ADD / REGISTER STUDENT
    // ==========================================

    public void addStudent(Student student) {

        boolean added =
                studentDAO.addStudent(student);

        if (added) {

            System.out.println(
                    "Student registered successfully!"
            );

        } else {

            System.out.println(
                    "Student registration failed!"
            );
        }
    }


    // ==========================================
    // 2. VIEW ALL STUDENTS
    // ==========================================

    public void viewAllStudents() {

        List<Student> students =
                studentDAO.getAllStudents();

        if (students.isEmpty()) {

            System.out.println(
                    "No students found!"
            );

            return;
        }

        System.out.println(
                "\n===== ALL STUDENTS ====="
        );

        for (Student student : students) {

            displayStudent(student);
        }
    }


    // ==========================================
    // 3. SEARCH STUDENT
    // ==========================================

    public Student searchStudent(
            int studentId
    ) {

        return studentDAO.searchStudent(
                studentId
        );
    }


    // ==========================================
    // 4. UPDATE STUDENT
    // ==========================================

    public boolean updateStudent(
            int studentId,
            String newPhone,
            String newEmail
    ) {

        return studentDAO.updateStudent(
                studentId,
                newPhone,
                newEmail
        );
    }


    // ==========================================
    // 5. DELETE STUDENT
    // ==========================================

    public boolean deleteStudent(
            int studentId
    ) {

        return studentDAO.deleteStudent(
                studentId
        );
    }


    // ==========================================
    // DISPLAY STUDENT DETAILS
    // ==========================================

    private void displayStudent(
            Student student
    ) {

        System.out.println(
                "\nStudent ID   : "
                        + student.studentId
        );

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

        System.out.println(
                "-----------------------------"
        );
    }
    // Search Student By Name
    public void searchStudentByName(String studentName) {

        studentDAO.searchStudentByName(studentName);
    }
}
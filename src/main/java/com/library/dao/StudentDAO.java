package com.library.dao;

import com.library.database.BookDatabase;
import com.library.database.DatabaseConnection;
import com.library.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // ==========================================
    // 1. CHECK IF STUDENT ID ALREADY EXISTS
    // ==========================================

//    public boolean studentExists(int studentId) {
//
//        String sql = "SELECT student_id FROM students WHERE studentId = ?";
//
//        try (
//                Connection connection = BookDatabase.getConnection();
//                PreparedStatement statement = connection.prepareStatement(sql)
//        ) {
//
//            statement.setInt(1, studentId);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                return resultSet.next();
//            }
//
//        } catch (SQLException e) {
//
//            System.out.println("Database error: " + e.getMessage());
//            return false;
//        }
//    }


    // ==========================================
    // 2. ADD / REGISTER STUDENT
    // ==========================================

    public boolean addStudent(Student student) {




        String sql = """
INSERT INTO students
(student_name, department, year, section,
roll_number, phone, email, address)
VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (

                Connection connection= BookDatabase.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, student.studentName);
            statement.setString(2, student.department);
            statement.setInt(3, student.year);
            statement.setString(4, student.section);
            statement.setString(5, student.rollNumber);
            statement.setString(6, student.phone);
            statement.setString(7, student.email);
            statement.setString(8, student.address);
            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Database error: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // ==========================================
    // 3. VIEW ALL STUDENTS
    // ==========================================

    public List<Student> getAllStudents() {

        List<Student> students =
                new ArrayList<>();

        String sql =
                "SELECT * FROM students";

        try (
                Connection connection= BookDatabase.getConnection();


                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Student student =
                        createStudent(resultSet);

                students.add(student);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database error: "
                            + e.getMessage()
            );
        }

        return students;
    }


    // ==========================================
    // 4. SEARCH STUDENT BY ID
    // ==========================================
    public Student searchStudent(int studentId) {

        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (
                Connection connection = BookDatabase.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, studentId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return createStudent(resultSet);
                }
            }

        } catch (SQLException e) {

            System.out.println("Database error: " + e.getMessage());
        }

        return null;
    }


    // ==========================================
    // 5. UPDATE STUDENT
    // ==========================================

    public boolean updateStudent(int studentId, String newPhone, String newEmail) {

        String sql = "UPDATE students SET phone = ?, email = ? WHERE student_id = ?";

        try (
                Connection connection = BookDatabase.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, newPhone);
            statement.setString(2, newEmail);
            statement.setInt(3, studentId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }


    // ==========================================
    // 6. DELETE STUDENT
    // ==========================================

    public boolean deleteStudent(int studentId) {

        String sql =
                "DELETE FROM students WHERE student_id = ?";

        try (
                Connection connection= BookDatabase.getConnection();


                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    studentId
            );

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Database error: "
                            + e.getMessage()
            );

            return false;
        }
    }


    private Student createStudent(
            ResultSet resultSet
    ) throws SQLException {
//student entity
        Student student =new Student();
        student.studentId=resultSet.getInt("student_id");



        student.studentName =
                resultSet.getString("student_name");

        student.department =
                resultSet.getString("department");

        student.year =
                resultSet.getInt("year");

        student.section =
                resultSet.getString("section");

        student.rollNumber =
                resultSet.getString("roll_number");

        student.phone =
                resultSet.getString("phone");

        student.email =
                resultSet.getString("email");

        student.address =
                resultSet.getString("address");

        return student;
    }
    public boolean isStudentExists(int studentId) {

        String query = "SELECT * FROM students WHERE student_id = ?";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    // Search Student By Name
    public void searchStudentByName(String studentName) {

        String query = "SELECT * FROM students WHERE student_name LIKE ?";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "%" + studentName + "%");

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("--------------------------------");
                System.out.println("Student ID : " + rs.getInt("student_id"));
                System.out.println("Student Name : " + rs.getString("student_name"));
                System.out.println("Department : " + rs.getString("department"));
                System.out.println("Year : " + rs.getInt("year"));
                System.out.println("Section : " + rs.getString("section"));
                System.out.println("Roll Number : " + rs.getString("roll_number"));
                System.out.println("Phone : " + rs.getString("phone"));
                System.out.println("Email : " + rs.getString("email"));
                System.out.println("Address : " + rs.getString("address"));
            }

            if (!found) {
                System.out.println("Student Not Found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
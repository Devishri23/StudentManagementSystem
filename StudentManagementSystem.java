import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
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
                    System.out.println("Thank You");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void addStudent() {

        try (Connection con = DBConnection.connect()) {

            if (con == null) return;

            sc.nextLine();

            System.out.print("Name : ");
            String name = sc.nextLine();

            System.out.print("Course : ");
            String course = sc.nextLine();

            System.out.print("Email : ");
            String email = sc.nextLine();

            System.out.print("Marks : ");
            double marks = sc.nextDouble();

            String sql = "INSERT INTO students(name,course,email,marks) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setString(3, email);
            ps.setDouble(4, marks);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewStudents() {

        try (Connection con = DBConnection.connect()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            System.out.println("\nID\tName\tCourse\tEmail\tMarks");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("course") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getDouble("marks"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void searchStudent() {

        try (Connection con = DBConnection.connect()) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String sql = "SELECT * FROM students WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("ID      : " + rs.getInt("id"));
                System.out.println("Name    : " + rs.getString("name"));
                System.out.println("Course  : " + rs.getString("course"));
                System.out.println("Email   : " + rs.getString("email"));
                System.out.println("Marks   : " + rs.getDouble("marks"));

            } else {
                System.out.println("Student Not Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateStudent() {

        try (Connection con = DBConnection.connect()) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("New Name: ");
            String name = sc.nextLine();

            System.out.print("New Course: ");
            String course = sc.nextLine();

            System.out.print("New Email: ");
            String email = sc.nextLine();

            System.out.print("New Marks: ");
            double marks = sc.nextDouble();

            String sql = "UPDATE students SET name=?,course=?,email=?,marks=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setString(3, email);
            ps.setDouble(4, marks);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Record Updated");
            else
                System.out.println("Student Not Found");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteStudent() {

        try (Connection con = DBConnection.connect()) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM students WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Record Deleted");
            else
                System.out.println("Student Not Found");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
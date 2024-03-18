// Rayhan Moraldo 101229564
import java.sql.*;

public class Main {

    // these variables are global so that all functions can connect to postgres with them
    static String url = "jdbc:postgresql://localhost:5432/A3Q1";
    static String user = "postgres";
    static String password = "Password";

    public static void main(String[] args){

        addStudent("Rayhan", "Moraldo", "rayhanmoraldo@cmail.carleton.ca",  (Date.valueOf("2021-09-01")));
        getAllStudents();
        updateStudentEmail(4, "juico@gmail.com");
        getAllStudents();
        deleteStudent(4);
        getAllStudents();
    }

    public static void getAllStudents(){
        // declare an empty string to add the output to
        String output = "";
        //try to connect to postgres and get data from the database
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM Students");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                output += "ID: " + resultSet.getString("student_id") + ", ";
                output += "First Name: " + resultSet.getString("first_name") + ", ";
                output += "Last Name: " + resultSet.getString("last_name") + ", ";
                output += "Email: " + resultSet.getString("email") + ", ";
                output += "Enrollment Date: " + resultSet.getString("enrollment_date");
                output += "\n";
            }

        }
        //handle any exceptions
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println(output);

    }

    public static void addStudent(String fn, String ln, String email, Date date) {
        try {
            // connect to db
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            // use the parameters to insert a student
            String insertSQL = "insert into Students (first_name, last_name, email, enrollment_date) values (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
                pstmt.setString(1,fn);
                pstmt.setString(2,ln);
                pstmt.setString(3,email);
                pstmt.setDate(4,date);
                pstmt.executeUpdate();

            }

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateStudentEmail(int sid, String new_email){
        try {
            //connect to db
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            // use the paratmers to update student email
            String updateSQL = "update Students set email = (?) where student_id = (?)";
            try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
                pstmt.setString(1, new_email);
                pstmt.setInt(2, sid);
                pstmt.executeUpdate();
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteStudent(int sid){
        try {
            // connect to db
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            // delete student with the id specified in parameters
            String deleteSQL = "delete from Students where student_id = (?)";
            try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, sid);
                pstmt.executeUpdate();
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }



}






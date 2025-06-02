//package hospital.management.system.Utilities;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//public class conn {
//
//    public Connection connection;
//    public Statement statement;
//
//
//    public conn(){
//
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root","12345");
//            statement = connection.createStatement();
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//        }
//
//
//
//
//    }
//}





package hospital.management.system.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class conn implements AutoCloseable {  // Implement AutoCloseable
    public Connection connection;
    public Statement statement;

    public conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Add driver loading
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_management_system",
                    "root", "12345");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}

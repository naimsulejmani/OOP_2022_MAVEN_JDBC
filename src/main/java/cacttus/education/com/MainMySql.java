package cacttus.education.com;

import java.sql.*;

public class MainMySql {
    public static void main(String[] args) {
        // emrin e kompjuterit; localhost; 127.0.0.1; ose veq pike .
        String connectionString =
                "jdbc:mysql://localhost:3306/sakila";

        try (
                Connection connection = DriverManager.getConnection(connectionString,"root","mysql");
                Statement stmt = connection.createStatement();
        ) {
            System.out.println("U konektuat me sukses!");
            boolean isQuery = stmt.execute("select * from sakila.actor");

            if(isQuery) {
                ResultSet resultSet = stmt.getResultSet();
                while(resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

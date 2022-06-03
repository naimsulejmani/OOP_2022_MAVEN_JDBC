package cacttus.education.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // emrin e kompjuterit; localhost; 127.0.0.1; ose veq pike .
        String connectionString =
                "jdbc:sqlserver://EXTREME:1433;databaseName=TSQL;user=user;password=user;encrypt=true;trustServerCertificate=true;";

        try (
                Connection connection = DriverManager.getConnection(connectionString);
                Statement stmt = connection.createStatement();
        ) {
            System.out.println("U konektuat me sukses!");
//boolean isQuery = stmt.execute(
//"INSERT INTO Production.Categories (categoryname, description) VALUES ('Pije','Pije freskuese');");
            boolean isQuery = stmt.execute("SELECT 1 as Nje");
            if(isQuery) {
                System.out.println("Shfaqi rezultatet");
            }
            else {
                System.out.println("U regjistrua me sukses!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}









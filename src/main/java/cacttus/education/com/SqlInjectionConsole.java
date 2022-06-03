package cacttus.education.com;

import java.sql.*;
import java.util.Scanner;

public class SqlInjectionConsole {
    public static void main(String[] args) {
        String connectionString =
                "jdbc:sqlserver://EXTREME:1433;databaseName=TSQL;user=user;password=user;encrypt=true;trustServerCertificate=true;";

        Scanner reader = new Scanner(System.in);
        System.out.println("Shkruani id-ne e produktit: ");
        String id = reader.nextLine();

        try (
                Connection connection = DriverManager.getConnection(connectionString);
                Statement stmt = connection.createStatement()
        ) {
            String query = "SELECT * FROM Production.Products WHERE productid=" + id;
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                int productId = resultSet.getInt(1);
                String productName = resultSet.getString("productname");
                int supplierId = resultSet.getObject(3, Integer.class);
                int categoryId = resultSet.getObject("categoryid", Integer.class);
                float unitPrice = resultSet.getFloat(5);
                boolean discontinued = resultSet.getBoolean("discontinued");

                System.out.printf("%d |  %s  |  %d  |  %d  |  %.2f  |  %b  |%n",
                        productId, productName, supplierId, categoryId, unitPrice, discontinued);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}

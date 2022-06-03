package cacttus.education.com;

import java.sql.*;
import java.util.Scanner;

public class SqlInjectionDefend {
    public static void main(String[] args) {
        String connectionString =
                "jdbc:sqlserver://EXTREME:1433;databaseName=TSQL;user=user;password=user;encrypt=true;trustServerCertificate=true;";

        Scanner reader = new Scanner(System.in);
        System.out.println("Shkruani id-ne e produktit: ");
        String id = reader.nextLine();
        //String qq = "INSERT INTO Production.Product(name, surname) values (?,?)";
        String query = "SELECT * FROM Production.Products WHERE productname=?";
        try (
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setObject(1, id, Types.VARCHAR);
            ResultSet resultSet = stmt.executeQuery();

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

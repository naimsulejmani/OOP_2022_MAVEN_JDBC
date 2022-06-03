package cacttus.education.com;

import java.sql.*;

public class ResultSetExample {
    public static void main(String[] args) {
        String connectionString =
                "jdbc:sqlserver://EXTREME:1433;databaseName=TSQL;user=user;password=user;encrypt=true;trustServerCertificate=true;";

        try (
                Connection connection = DriverManager.getConnection(connectionString);
                Statement stmt = connection.createStatement()
        ) {
            String query = "SELECT * FROM Production.Products";
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

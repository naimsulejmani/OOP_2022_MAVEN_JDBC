package cacttus.education.com;

import java.sql.*;

public class CallableStatementExample {
    public static void main(String[] args) {
        String connectionString =
                "jdbc:sqlserver://EXTREME:1433;databaseName=TSQL;user=user;password=user;encrypt=true;trustServerCertificate=true;";
        String query = "{CALL usp_Product_Insert(?,?,?,?,?)}";
        try (
                Connection connection = DriverManager.getConnection(connectionString);
                CallableStatement stmt = connection.prepareCall(query);
        ) {
            stmt.setObject("prm_productname", "Laptop X", Types.VARCHAR);
            stmt.setInt(2, 1);
            stmt.setInt(3, 1);
            stmt.setFloat("prm_unitprice", 1400f);
            stmt.setBoolean(5, false);

            stmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

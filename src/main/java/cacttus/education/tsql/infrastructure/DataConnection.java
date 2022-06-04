package cacttus.education.tsql.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    private static Connection connection;//kena me ruajt koneksionin tone;
    private static String connectionUrl =
            "jdbc:sqlserver://EXTREME:1433;databaseName=TSQL;user=user;password=user;encrypt=true;trustServerCertificate=true;";

    private DataConnection() {
        //default konstruktori mirepo privat mos me kriju objekt prej kesaj
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(connectionUrl);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static String getConnectionUrl() {
        return connectionUrl;
    }

    public static void setConnectionUrl(String connectionUrl) {
        DataConnection.connectionUrl = connectionUrl;
    }
}

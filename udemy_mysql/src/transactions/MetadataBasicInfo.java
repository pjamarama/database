package transactions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.io.FileReader;

public class MetadataBasicInfo {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        Connection connection = establishConnection();
        try {
            DatabaseMetaData metadata = connection.getMetaData();
            System.out.println("Product name: " + metadata.getDatabaseProductName());
            System.out.println("Product version: " + metadata.getDatabaseProductVersion());

            System.out.println("JDBC driver name: " + metadata.getDriverName());
            System.out.println("JDBC driver version: " + metadata.getDriverVersion());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    private static Connection establishConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(
                "/Users/alexey/IdeaProjects/db/udemy_mysql/src/dbconnection.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String pass = properties.getProperty("pass");
        return DriverManager.getConnection(url, user, pass);
    }

    private static void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

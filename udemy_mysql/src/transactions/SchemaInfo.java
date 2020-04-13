package transactions;

import java.io.IOException;
import java.sql.*;
import java.io.FileReader;
import java.util.Properties;

public class SchemaInfo {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = establishConnection();
        ResultSet resultSet = null;

        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String columnNamePattern = null;
        String[] types = null;

        try {
            DatabaseMetaData metadata = connection.getMetaData();

//            Get list of tables
            System.out.println("List of tables: ");
            resultSet = metadata.getTables(catalog, schemaPattern, tableNamePattern, types);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("TABLE_NAME"));
            }

//            Get list of columns
            System.out.println("List of columns: ");
            resultSet = metadata.getColumns(catalog, schemaPattern, "employees", columnNamePattern);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("COLUMN_NAME"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet, connection);
        }
    }

    private static Connection establishConnection() throws SQLException, IOException {
        Properties p = new Properties();
        p.load(new FileReader("/Users/alexey/IdeaProjects/db/udemy_mysql/src/dbconnection.properties"));
        String username = p.getProperty("user");
        String password = p.getProperty("pass");
        String url = p.getProperty("url");
        return DriverManager.getConnection(url, username, password);
    }

    private static void close(ResultSet rs, Connection conn) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}

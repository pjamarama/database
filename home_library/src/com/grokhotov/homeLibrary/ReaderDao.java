package com.grokhotov.homeLibrary;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ReaderDao implements AutoCloseable {
    private Connection connection;
    Scanner scanner = new Scanner(System.in);

    public ReaderDao() throws IOException, SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileReader("dbConnection.properties"));
//        ?
        Connection connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("pass")
        );
//        ?
        this.connection = connection;
    }

    public void createReader() throws SQLException {
        System.out.println("Enter readers name and phone number\n " +
                "in format first_name last_name phone_number:\n");
        String[] input = scanner.nextLine().split(" ");
        String firstName = input[0];
        String lastName = input[1];
        String phoneNumber = input[2];

        PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                "home_library.reader (readers_first_name, readers_last_name, phone_number) " +
                "VALUES (?,?,?)");
        ps.setString(1,firstName);
        ps.setString(2,lastName);
        ps.setString(3, phoneNumber);
        ps.execute();
        System.out.println("Reader added");
    }

    public void deleteReader(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM home_library.reader WHERE reader_id=?"
        );
        ps.setInt(1, id);
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

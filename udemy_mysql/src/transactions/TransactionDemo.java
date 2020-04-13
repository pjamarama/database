/**
 * delete all HR records
 * add 300 000 to all Engineering records
 */

package transactions;

import java.io.*;
import java.sql.*;
import java.util.*;

public class TransactionDemo {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        Connection conn = null;
        Statement statement = null;

        try {
            conn = loadProperties();

//            Turn off autocommit
            conn.setAutoCommit(false);

//            Show salaries before
            showSalaries(conn, "HR");
            showSalaries(conn, "Engineering");

//            Transaction step 1: Delete all HR employees
            statement = conn.createStatement();
            statement.executeUpdate("delete from employees where department = 'HR'");

//            Transaction step 1: Set salaries to 300 000 to all Engineering
            statement.executeUpdate("update employees set salary=300000 " +
                    "where department = 'Engineering'");

            System.out.println("Transaction steps are ready");

//            Ask user if it is okay to save
            boolean okayToSave = askIfOkayToSave();
            if (okayToSave) {
                conn.commit();
                System.out.println("Transaction committed");
            } else {
                conn.rollback();
                System.out.println("Transaction rolled back");
            }

//            Show salaries after
            showSalaries(conn, "HR");
            showSalaries(conn, "Engineering");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, statement, null);
        }
    }

    private static Connection loadProperties() throws FileNotFoundException, IOException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileReader(
                "/Users/alexey/IdeaProjects/db/udemy_mysql/src/dbconnection.properties")
        );
        Connection conn = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("pass")
        );
        return conn;
    }

    private static void showSalaries(Connection c, String department) throws SQLException {
        System.out.println(department + " department salary: ");
        PreparedStatement ps = c.prepareStatement("select * from employees where department = ?");
        ps.setString(1, department);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String firstName = rs.getString(3);
            String lastName = rs.getString(2);
            int salary = rs.getInt(6);
            System.out.printf("%s %s: %d\n", firstName, lastName, salary);
        }
        System.out.println();
    }

    private static boolean askIfOkayToSave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Commit changes? (y/n)");
        String input = scanner.nextLine();
        scanner.close();
        return input.equalsIgnoreCase("y");
    }

    private static void close(Connection c, Statement s, ResultSet rs) throws SQLException {
        if (c != null) {
            c.close();
        }

        if (s != null) {
            s.close();
        }

        if (rs != null) {
            rs.close();
        }
    }

    private static void close(PreparedStatement ps, ResultSet rs) throws SQLException {
        close(null, ps, rs);
    }

}

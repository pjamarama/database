import java.sql.*;

public class DemoSql {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String user = "agrokhotov";
        String pass = "123Sql";
        try {
//        1. Get a connection to database
            connection = DriverManager.getConnection(dbUrl, user, pass);

//        2. Create a statement
            PreparedStatement ps = connection.prepareStatement(
                    "select * from employees where salary > ? and department = ?"
            );

            ps.setInt(1, 80000);
            ps.setString(2,"Legal");

            reusablePreparedStatement(ps);

//        3. Execute SQL-query
            ResultSet rs = ps.executeQuery();
            displayResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    private static void displayResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString(3) + " " + rs.getString(2) +
                    " " + rs.getInt(6));
        }
    }

    private static void reusablePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, 25000);
        preparedStatement.setString(2, "HR");
    }
}

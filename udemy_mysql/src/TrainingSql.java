import java.sql.*;

public class TrainingSql {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String user = "agrokhotov";
        String pass = "123Sql";

        connection = DriverManager.getConnection(dbUrl, user, pass);
        statement = connection.createStatement();
//        resultSet = statement.executeQuery("select * from employees");
/*        statement.executeUpdate(
                "insert into employees(last_name, first_name, email, department, salary) " +
                        "values ('Keen', 'Michael', 'mkeen@umbrella.com', 'Security', 60000.00)"
        );*/



//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("first_name") + "\t" +
//                    resultSet.getString("last_name") + "\t" +
//                    resultSet.getString("department"));
//        }

    }
}

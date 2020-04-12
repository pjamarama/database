package storedProcedures;

import java.sql.*;
import java.util.concurrent.Callable;

public class InParameter {
    public static void main(String[] args) throws SQLException {
        CallableStatement statement = null;
        Connection connection = null;

        String url = "jdbc:mysql://localhost:3306/demo";
        String user = "agrokhotov";
        String pass = "123Sql";

        try {
            connection = DriverManager.getConnection(url, user, pass);
            String dept = "Engineering";
            int increase_amount = 10000;

            statement = connection.prepareCall("{call increase_salaries_for_department(?,?)}");
            statement.setString(1, dept);
            statement.setDouble(2, increase_amount);
            statement.execute();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}

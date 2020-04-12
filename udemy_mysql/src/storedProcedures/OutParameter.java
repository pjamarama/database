package storedProcedures;

import java.sql.*;

public class OutParameter {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement ;

        String url = "jdbc:mysql://localhost:3306/demo";
        String user = "agrokhotov";
        String pass = "123Sql";

        try {
            String theDepartment = "Engineering";
            connection = DriverManager.getConnection(url, user, pass);
            callableStatement = connection.prepareCall("{call get_count_for_department(?,?)}");
            callableStatement.setString(1, theDepartment);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();

            int theCount = callableStatement.getInt(2);
            System.out.println("\ntheCount = " + theCount);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}

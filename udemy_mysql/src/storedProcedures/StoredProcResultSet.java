package storedProcedures;

import java.sql.*;

public class StoredProcResultSet {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        ResultSet resultSet = null;

        String url = "jdbc:mysql://localhost:3306/demo";
        String user = "agrokhotov";
        String pass = "123Sql";

        try {
            String theDepartment = "Engineering";
            conn = DriverManager.getConnection(url, user, pass);
            CallableStatement callableStatement = conn.prepareCall("{call get_employees_for_department(?)}");
            callableStatement.setString(1, theDepartment);
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                System.out.println(resultSet.getString(3) + " " + resultSet.getString(2) +
                        " " + resultSet.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}

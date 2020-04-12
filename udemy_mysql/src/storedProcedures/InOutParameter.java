package storedProcedures;

import java.sql.*;

public class InOutParameter {
    public static void main(String[] args)  throws SQLException {
        Connection conn = null;
        CallableStatement callableStatement = null;

        String url = "jdbc:mysql://localhost:3306/demo";
        String user = "agrokhotov";
        String pass = "123Sql";

        try {
            conn = DriverManager.getConnection(url, user, pass);
            callableStatement = conn.prepareCall("{call greet_the_department(?)}");
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(1, "Engineering");
            callableStatement.execute();
            String result = callableStatement.getString(1);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }
}

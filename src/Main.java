import java.sql.*;

import static config.SqlDB.DB_CONNECTION;
import static config.sql_config_connection.SQL_PASSWORD;
import static config.sql_config_connection.SQL_USERNAME;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection(DB_CONNECTION,
                SQL_USERNAME, SQL_PASSWORD);

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT product_id, product_name, MIN(product_price) AS price  FROM product;");

        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1)+":"+resultSet.getString(2)+":"+resultSet.getDouble(3));
        }
    }
}
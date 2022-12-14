package viewmodel;

import com.mysql.cj.protocol.Resultset;
import model.ProductItem;

import java.sql.*;
import java.util.ArrayList;

import static config.SqlDB.DB_CONNECTION;
import static config.sql_config_connection.SQL_PASSWORD;
import static config.sql_config_connection.SQL_USERNAME;

public class ViewProductViewModel {


    public String[][] displayProduct() {
        ArrayList<String[]> data = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(DB_CONNECTION, SQL_USERNAME, SQL_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM product");

            while (result.next()) {
                data.add(new String[]{String.valueOf(result.getInt(1)),String.valueOf(result.getString(2)),String.valueOf(result.getDouble(3))});

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data.toArray(new String[0][]);
    }
}

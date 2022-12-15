package viewmodel;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import static config.SqlDB.DB_CONNECTION;
import static config.sql_config_connection.SQL_PASSWORD;
import static config.sql_config_connection.SQL_USERNAME;

public class ViewProductViewModel {

    public Object[][] displayProduct() {
        ArrayList<Object[]> dataList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DB_CONNECTION, SQL_USERNAME, SQL_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM product");

            while (result.next()) {
                String path = "product_images/" + result.getString(2) + ".png";

                if (!new File(path).exists())
                    path = "product_images/default.png";

                dataList.add(new Object[]{String.valueOf(result.getInt(1)),result.getString(2),
                                      String.valueOf(result.getDouble(3)),
                                      new ImageIcon(path)});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return dataList.toArray(new Object[0][]);
    }
}
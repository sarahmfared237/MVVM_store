package viewmodel;



import model.ProductItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;


import static config.SqlDB.DB_CONNECTION;
import static config.sql_config_connection.SQL_PASSWORD;
import static config.sql_config_connection.SQL_USERNAME;

public class AddProductViewModel {

    final ArrayList<ValidationObserver> validationObserverList = new ArrayList<>();

    public void validateThenAdd(String product_name, String product_price, String path) {
        try
        {
            String name = product_name.trim();
            double price = Double.parseDouble(product_price);

            if (price < 0 || name.equals("")) {
                notify_observers(false);
                return;
            }
            String pathDis;

            if (!path.equals("")) {
                pathDis = "product_images/" + name + ".png";

                Files.copy(new File(path).toPath(), new File(pathDis).toPath());
            }

            if (!addDB(new ProductItem(name, price)))
                return;

            notify_observers(true);

        } catch (NumberFormatException e) {
            notify_observers(false);
        } catch (IOException e) {
            notify_observers(true);
        }

    }

    private boolean addDB(ProductItem productItem) {

        try {
            Connection con = DriverManager.getConnection(DB_CONNECTION, SQL_USERNAME, SQL_PASSWORD);
            String qurey = "INSERT INTO product (product_name, product_price) VALUES (?, ?);";
            PreparedStatement statement = con.prepareStatement(qurey);
            statement.setString(1, productItem.getProductName());
            statement.setDouble(2, productItem.getProductPrice());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            notify_observers(false);
            return false;
        }
    }

    public void addObserver(ValidationObserver observer) {
        validationObserverList.add(observer);
    }

    private void notify_observers(boolean state) {
        for (ValidationObserver observer : validationObserverList) {
            observer.update(state);
        }
    }

}

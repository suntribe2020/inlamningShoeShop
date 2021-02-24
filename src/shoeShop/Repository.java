package shoeShop;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Katri Vidén
 * Date: 2021-02-18
 * Time: 17:47
 * Project: inlamningShoeShop
 * Copyright: MIT
 */
public class Repository {

    Properties properties = new Properties();

    public Repository() {

        try (FileInputStream fileInputStream = new FileInputStream("properties/connection.properties")) {
            properties.load(fileInputStream);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int addToCart(int customerId, int ordersId, int shoeId, int quantity, String date) {
        ResultSet resultSet = null;
        String query = "call addToCart(?, ?, ?, ?, ?)";
        int currentOrderId = -1;

        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"));
             CallableStatement statement = connection.prepareCall(query)) {

            statement.setInt(1, customerId);
            statement.setInt(2, ordersId);
            statement.setInt(3, shoeId);
            statement.setInt(4, quantity);
            statement.setString(5, date);
            statement.registerOutParameter(2, Types.INTEGER);

            resultSet = statement.executeQuery();
            String errormessage = "";

            while (resultSet != null && resultSet.next()) {
                errormessage = resultSet.getString("error");
            }
            if (!errormessage.equals("")) {
                System.out.println("ERROR: " + errormessage);
                return -1;
            }

            return statement.getInt(2);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not add shoe to cart");
        }
        return -1;
    }

    public List<CartItem> getListOfCustomersOrderedItems(int customerId, int currentOrderId) {
        List<CartItem> cartItem = new ArrayList<>();
        ResultSet resultSet = null;
        String query = "select orders.date, brand.name, shoe.name, shoe.size, shoe.color, shoe.price, orderDetails.quantity from shoe " +
                "inner join brand on shoe.brandId = brand.id " +
                "inner join orderDetails on orderDetails.shoeId = shoe.id " +
                "inner join orders on orderDetails.ordersId = orders.id " +
                "inner join customer on orders.customerId = customer.id where customer.id = ? and orders.id = ?";

        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"));
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, currentOrderId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cartItem.add(new CartItem(resultSet.getString("date"), resultSet.getString("brand.name"),
                        resultSet.getString("shoe.name"), resultSet.getInt("size"), resultSet.getString("color"),
                        resultSet.getInt("price"), resultSet.getInt("quantity")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItem;
    }

    public List<Shoe> getAllShoes() {
        List<Shoe> shoes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"));
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from shoe");) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int size = resultSet.getInt("size");
                String color = resultSet.getString("color");
                int price = resultSet.getInt("price");
                int inStock = resultSet.getInt("inStock");

                shoes.add(new Shoe(id, name, size, color, price, inStock));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shoes;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"));
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from customer");) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                int postalNumber = resultSet.getInt("postalNumber");
                String city = resultSet.getString("city");
                String userName = resultSet.getString("userName");
                String userPassword = resultSet.getString("userPassword");

                customers.add(new Customer(id, name, address, postalNumber, city, userName, userPassword));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}




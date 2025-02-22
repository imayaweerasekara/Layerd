package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {

    public static boolean save(CustomerDto customer) throws SQLException {
        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getId());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getTel());
        pstm.setObject(4,customer.getEmail());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(CustomerDto customer) throws SQLException {
        String sql = "UPDATE customer SET name = ?, contact = ?, email =? WHERE customer_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getTel());
        pstm.setObject(3, customer.getEmail());
        pstm.setObject(4, customer.getId());

        return pstm.executeUpdate() > 0;
    }
    public static CustomerDto searchById(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String email = resultSet.getString(4);

            CustomerDto customer = new CustomerDto(cus_id, name, tel, email);

            return customer;
        }

        return null;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE customer_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<CustomerDto> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<CustomerDto> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String email = resultSet.getString(4);

            CustomerDto customer = new CustomerDto(id, name, tel, email);
            cusList.add(customer);
        }
        return cusList;
    }

    public static List<String> getIds(String id) throws SQLException {
        String sql = "SELECT customer_id FROM customer";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    public static CustomerDto searchByContact(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE contact = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String email = resultSet.getString(4);

            CustomerDto customer = new CustomerDto(cus_id, name, tel, email);

            return customer;
        }

        return null;
    }
    public static List<String> searchById() throws SQLException {
        String sql = "SELECT customer_id FROM customer";

        List<String> ids = new ArrayList<>();

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);

        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }

        return ids;
    }
}

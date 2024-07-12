package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.ItemDTO;
import lk.Ijse.model.order_itemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
    public static boolean save(ItemDTO item) throws SQLException {
        String sql = "INSERT INTO item VALUES(?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, item.getCode());
        pstm.setObject(2, item.getName());
        pstm.setObject(3, item.getDescription());
        pstm.setObject(4, item.getCategory());
        pstm.setObject(5, item.getPrice());
        pstm.setObject(6, item.getQtyOnHand());

        return pstm.executeUpdate() > 0;
    }
    public static boolean update(ItemDTO item) throws SQLException {
        String sql = "UPDATE item SET name = ?, Description=?, Category = ?, price = ?, Qty_On_Hand = ? WHERE item_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1, item.getName());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getCategory());
        pstm.setObject(4, item.getPrice());
        pstm.setObject(5, item.getQtyOnHand());
        pstm.setObject(6, item.getCode());

        return pstm.executeUpdate() > 0;
    }

    public static List<ItemDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM item";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ItemDTO> itemList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String des = resultSet.getString(3);
            String category = resultSet.getString(4);
            double price = Double.parseDouble(resultSet.getString(5));
            int qty = 0;   //this is line 63
            try {
                String qtyString = resultSet.getString(6);
                if (qtyString != null && !qtyString.isEmpty()) {
                    qty = Integer.parseInt(qtyString);
                }
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails
                // You can log an error, set a default value, or take other appropriate action
                e.printStackTrace(); // Example: Print the stack trace for debugging
            }

            ItemDTO item = new ItemDTO(id, name, des, category,price,qty);
            itemList.add(item);
        }
        return itemList;
    }

    public static ItemDTO searchByCode(String code) throws SQLException {
        String sql = "SELECT * FROM item WHERE item_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, code);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6)
            );
        }
        return null;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM item WHERE item_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<String> getcode() throws SQLException {
        String sql = "SELECT item_id FROM item";
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection()
                .prepareStatement(sql)
                .executeQuery();

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    public static boolean updateQty(List<order_itemDTO> odList) throws SQLException {
        for (order_itemDTO od : odList) {
            if (!updateQty(od)) {
                return false;
            }
        }
        return true;
    }
    private static boolean updateQty(order_itemDTO od) throws SQLException {
        String sql = "UPDATE item SET Qty_On_Hand = Qty_On_Hand - ? WHERE item_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, od.getQty());
        pstm.setString(2, od.getItemID());

        return pstm.executeUpdate() > 0;
    }
}

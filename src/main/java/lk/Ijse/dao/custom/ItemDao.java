package lk.Ijse.dao.custom;

import lk.Ijse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao {
    ArrayList<Item> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String code) throws SQLException, ClassNotFoundException;

    boolean add(Item entity) throws SQLException, ClassNotFoundException;

    boolean update(Item entity) throws SQLException, ClassNotFoundException;

    boolean exist(String code) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    Item search(String code) throws SQLException, ClassNotFoundException;
}

package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.ItemBo;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.ItemDao;
import lk.Ijse.dto.ItemDTO;
import lk.Ijse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allEntityData = itemDao.getAll();
        ArrayList<ItemDTO> allDTOData= new ArrayList<>();
        for (Item i : allEntityData) {
            allDTOData.add(new ItemDTO(i.getCode(),i.getName(),i.getDescription(),i.getCategory(),i.getPrice(),i.getQtyOnHand()));
        }
        return allDTOData;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDao.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDao.add(new Item(dto.getCode(), dto.getName(), dto.getDescription(), dto.getCategory(), dto.getPrice(), dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(dto.getCode(), dto.getName(), dto.getDescription(), dto.getCategory(), dto.getPrice(), dto.getQtyOnHand()));
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDao.exist(code);
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return itemDao.generateNewID();
    }
}

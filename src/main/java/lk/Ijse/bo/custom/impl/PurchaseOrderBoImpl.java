package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.PurchaseOrderBo;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.CustomerDao;
import lk.Ijse.dao.custom.ItemDao;
import lk.Ijse.dao.custom.OrderDao;
import lk.Ijse.dao.custom.OrderDetailsDao;

public class PurchaseOrderBoImpl implements PurchaseOrderBo {
    CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDao orderDao = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDao orderDetailsDAO = (OrderDetailsDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);


}

package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.CustomerBo;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.CustomerDao;
import lk.Ijse.dto.CustomerDto;
import lk.Ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl  implements  CustomerBo{

    CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDao.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDto(c.getId(),c.getName(),c.getTel(),c.getEmail()));
        }
        return allCustomers;
    }

    @Override
    public boolean add(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.add(new Customer(dto.getId(), dto.getName(), dto.getTel(), dto.getEmail()));
    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(dto.getId(),dto.getName(),dto.getTel(), dto.getEmail()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return customerDao.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDao.generateNewID();
    }
}

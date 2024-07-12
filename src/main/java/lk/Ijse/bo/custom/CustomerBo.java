package lk.Ijse.bo.custom;

import lk.Ijse.bo.SuperBo;
import lk.Ijse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo {

    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException;

    public boolean add(CustomerDto dto) throws SQLException, ClassNotFoundException ;

    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException ;

    public boolean exist(String id) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public String generateNewCustomerID() throws SQLException, ClassNotFoundException;


}

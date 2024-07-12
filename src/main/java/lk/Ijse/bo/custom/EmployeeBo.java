package lk.Ijse.bo.custom;

import lk.Ijse.bo.SuperBo;
import lk.Ijse.dto.CustomerDto;
import lk.Ijse.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBo extends SuperBo{
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean add(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean exist(String id) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}

package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.EmployeeBo;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.EmployeeDao;
import lk.Ijse.dto.EmployeeDTO;
import lk.Ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBoImpl implements EmployeeBo {

    EmployeeDao employeeDao = (EmployeeDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);


    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployees= new ArrayList<>();
        ArrayList<Employee> all = employeeDao.getAll();
        for (Employee emp: all) {
            allEmployees.add(new EmployeeDTO(emp.getId(),emp.getName(),emp.getTel(),emp.getSalary(),emp.getRole()));
        }
        return allEmployees;
    }

    @Override
    public boolean add(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDao.add(new Employee(dto.getId(), dto.getName(), dto.getTel(), dto.getSalary(), dto.getRole()));
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDao.update(new Employee(dto.getId(),dto.getName(),dto.getTel(), dto.getSalary(), dto.getRole()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDao.exist(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return employeeDao.generateNewID();
    }
}


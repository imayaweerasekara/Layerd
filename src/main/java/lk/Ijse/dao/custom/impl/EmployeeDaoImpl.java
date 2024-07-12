package lk.Ijse.dao.custom.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.custom.EmployeeDao;
import lk.Ijse.entity.Employee;
import lk.Ijse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");
        while (rst.next()) {
            allEmployees.add(new Employee(rst.getString("id"), rst.getString("name"), rst.getString("tel"),rst.getString("salary"),  rst.getString("role")));
        }
        return allEmployees;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee (id, name, tel, salary, role) VALUES (?,?,?,?,?)",entity.getId(),entity.getName(),entity.getTel(),entity.getSalary(),entity.getRole());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET name=?, tel=?, salary=?, role=? WHERE id=?",entity.getName(),entity.getTel(),entity.getSalary(),entity.getRole(),entity.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Employee WHERE id=?", id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Employee ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            String newEmployeeId = id.replace("I00", "") + 1;
            return String.format("E00-%03d", newEmployeeId);
        } else {
            return "E001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE id=?", id);
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM Employee WHERE id=?",id+"");
        rst.next();
        return new Employee(id + "", rst.getString("name"), rst.getString("tel"), rst.getString("salary"), rst.getString("role"));
    }
}

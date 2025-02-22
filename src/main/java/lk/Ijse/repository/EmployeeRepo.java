package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static boolean save(EmployeeDTO employee) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getTel());
        pstm.setObject(4, employee.getSalary());
        pstm.setObject(5,employee.getRole());

        return pstm.executeUpdate() > 0;
    }
    public static boolean update(EmployeeDTO employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?, contact = ?,salary=?, Role =? WHERE employee_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getTel());
        pstm.setObject(3, employee.getSalary());
        pstm.setObject(4, employee.getRole());
        pstm.setObject(5, employee.getId());

        return pstm.executeUpdate() > 0;
    }
    public static EmployeeDTO searchById(String id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String salary = resultSet.getString(4);
            String role = resultSet.getString(5);

            EmployeeDTO employee = new EmployeeDTO(emp_id, name, tel, salary,role);

            return employee;
        }

        return null;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE employee_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<EmployeeDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<EmployeeDTO> empList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String salary = resultSet.getString(4);
            String role = resultSet.getString(5);

            EmployeeDTO employee = new EmployeeDTO(id, name, tel, salary,role);
            empList.add(employee);
        }
        return empList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT employee_id FROM employee";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public static List<String> searchById() throws SQLException {
        String sql = "SELECT employee_id FROM employee";

        List<String> ids = new ArrayList<>();

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);

        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }

        return ids;
    }
}


package lk.Ijse.entity;

public class Employee {
    String id;
    String name;
    String Tel;
    String salary;
    String Role;

    public Employee(String id, String name, String tel, String salary, String role) {
        this.id = id;
        this.name = name;
        Tel = tel;
        this.salary = salary;
        Role = role;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", Tel='" + Tel + '\'' +
                ", salary='" + salary + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}

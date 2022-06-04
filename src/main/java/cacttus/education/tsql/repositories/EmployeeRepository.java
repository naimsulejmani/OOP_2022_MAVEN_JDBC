package cacttus.education.tsql.repositories;

import cacttus.education.tsql.infrastructure.BaseRepository;
import cacttus.education.tsql.infrastructure.DataConnection;
import cacttus.education.tsql.infrastructure.TableToClassConvertable;
import cacttus.education.tsql.models.Employee;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements BaseRepository<Employee, Integer>, TableToClassConvertable<Employee> {
    @Override
    public List<Employee> getAll() {
        List<Employee> employees = null;

        try (
                Connection connection = DataConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {
            String query = "SELECT * FROM HR.Employees";
            ResultSet resultSet = statement.executeQuery(query);
            employees = new ArrayList<>();
            while (resultSet.next()) { //perderisa resultset ka elemente ne rradhe
                Employee employee = convert(resultSet);
//                employee.setEmpId(resultSet.getInt("empid"));
//                employee.setLastName(resultSet.getString(2));
//                employee.setFirstName(resultSet.getString(3));
//                employee.setTitle(resultSet.getString(4));
//                employee.setTitleOfCourtesy(resultSet.getString(5));
//                employee.setBirthdate(resultSet.getDate(6).toLocalDate());
//                employee.setHireDate(resultSet.getDate(7).toLocalDate());
//                employee.setAddress(resultSet.getString(8));
//                employee.setCity(resultSet.getString(9));
//                if (resultSet.getObject(10) != null)
//                    employee.setRegion(resultSet.getString(10));
//                employee.setPostalCode(resultSet.getString(11));
//                employee.setCountry(resultSet.getString(12));
//                employee.setPhone(resultSet.getString(13));
//                if (resultSet.getObject(14) != null)
//                    employee.setMgRid(resultSet.getInt(14));
                employees.add(employee);
            }
            return employees;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee get(Integer key) {
        Employee employee = null;
        String query = "SELECT * FROM HR.Employees WHERE empid = ?";

        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee = convert(resultSet);
            }
            return employee;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Employee item) {
        String query =
                "INSERT INTO HR.Employees (lastname,firstname,title,titleofcourtesy,birthdate," +
                        "hiredate,address,city,region,postalcode,country,phone,mgrid)" +
                        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, item.getLastName());
            statement.setString(2, item.getFirstName());
            statement.setString(3, item.getTitle());
            statement.setString(4, item.getTitleOfCourtesy());
            statement.setDate(5, Date.valueOf(item.getBirthdate()));
            statement.setDate(6, Date.valueOf(item.getHireDate()));
            statement.setString(7, item.getAddress());
            statement.setString(8, item.getCity());
            statement.setString(9, item.getRegion());
            statement.setString(10, item.getPostalCode());
            statement.setString(11, item.getCountry());
            statement.setString(12, item.getPhone());
            //statement.setInt(13, item.getMgRid());
            if (item.getMgRid() == 0)
                statement.setObject(13, null, Types.INTEGER);
            else
                statement.setInt(13, item.getMgRid());
            int rows = statement.executeUpdate();

            return rows >= 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean modify(Employee item) {
        String query = "UPDATE HR.Employees SET lastname = ?, firstname = ? WHERE empid = ?";

        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, item.getLastName());
            statement.setString(2, item.getFirstName());
            statement.setInt(3, item.getEmpId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(Employee item) {
        return removeById(item.getEmpId());
    }

    @Override
    public boolean removeById(Integer key) {
        String query = "DELETE FROM HR.Employees WHERE empid = ?";
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, key);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee convert(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setEmpId(resultSet.getInt("empid"));
        employee.setLastName(resultSet.getString(2));
        employee.setFirstName(resultSet.getString(3));
        employee.setTitle(resultSet.getString(4));
        employee.setTitleOfCourtesy(resultSet.getString(5));
        employee.setBirthdate(resultSet.getDate(6).toLocalDate());
        employee.setHireDate(resultSet.getDate(7).toLocalDate());
        employee.setAddress(resultSet.getString(8));
        employee.setCity(resultSet.getString(9));
        if (resultSet.getObject(10) != null)
            employee.setRegion(resultSet.getString(10));
        employee.setPostalCode(resultSet.getString(11));
        employee.setCountry(resultSet.getString(12));
        employee.setPhone(resultSet.getString(13));
        if (resultSet.getObject(14) != null)
            employee.setMgRid(resultSet.getInt(14));
        return employee;
    }
}

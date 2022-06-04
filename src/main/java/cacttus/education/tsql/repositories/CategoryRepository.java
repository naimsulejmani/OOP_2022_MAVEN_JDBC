package cacttus.education.tsql.repositories;

import cacttus.education.tsql.infrastructure.DataConnection;
import cacttus.education.tsql.infrastructure.TableToClassConvertable;
import cacttus.education.tsql.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements cacttus.education.tsql.infrastructure.CategoryRepository,
        TableToClassConvertable<Category> {
    @Override
    public List<Category> getAll() {
        List<Category> categories = null;
        try (
                Connection connection = DataConnection.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Production.Categories");
            categories = new ArrayList<>();
            while (resultSet.next()) {
                categories.add(convert(resultSet));
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category get(Integer key) {
        Category category = null;
        String query = "SELECT * FROM Production.Categories WHERE categoryid = ?";
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = convert(resultSet);
            }
            return category;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Category item) {
        return false;
    }

    @Override
    public boolean modify(Category item) {
        return false;
    }

    @Override
    public boolean remove(Category item) {
        return false;
    }

    @Override
    public boolean removeById(Integer key) {
        return false;
    }

    @Override
    public List<Category> getAllByNameLike(String filter) {
        List<Category> categories = null;
        String query = "SELECT * FROM Production.Categories WHERE categoryname LIKE '%' + ? + '%'";

        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, filter);
            ResultSet resultSet = statement.executeQuery();
            categories = new ArrayList<>();
            while (resultSet.next()) {
                categories.add(convert(resultSet));
            }
            return categories;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int count() {
        String query = "SELECT COUNT(*) as Nr FROM Production.Categories";
        try (
                Connection connection = DataConnection.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next())
                return resultSet.getInt("Nr");
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category convert(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setCategoryId(resultSet.getInt(1));
        category.setCategoryName(resultSet.getString(2));
        category.setDescription(resultSet.getString(3));
        return category;
    }
}

package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
    {
        // get all categories
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving categories", e);
        }

        return categories;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapRow(resultSet); // Map the row to a Category object
            } else {
                throw new RuntimeException("Category with ID " + categoryId + " not found."); // Handle missing category
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving category with ID: " + categoryId, e);
        }
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int categoryId = generatedKeys.getInt(1);
                    return getById(categoryId); // Fetch and return the newly created category
                } else {
                    throw new RuntimeException("Failed to retrieve the generated ID for the new category.");
                }
            } else {
                throw new RuntimeException("Category creation failed; no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating category", e);
        }
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, categoryId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating category with ID: " + categoryId, e);
        }
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
        String sql = "DELETE FROM categories WHERE category_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting category with ID: " + categoryId, e);
        }
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}

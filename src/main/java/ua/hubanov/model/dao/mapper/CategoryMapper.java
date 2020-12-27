package ua.hubanov.model.dao.mapper;

import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CategoryMapper implements ObjectMapper<Category> {
    @Override
    public Category extractFromResultSet(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("categories.id"));
        category.setName(rs.getString("categories.name"));
        return category;
    }

    @Override
    public Category makeUnique(Map<Long, Category> cache, Category entity) {
        return null;
    }
}

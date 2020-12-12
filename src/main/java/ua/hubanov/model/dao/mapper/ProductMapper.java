package ua.hubanov.model.dao.mapper;

import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProductMapper implements ObjectMapper<Product> {
    @Override
    public Product extractFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setDescription(rs.getString("description"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setPublicationDate(rs.getDate("date"));
        product.setQuantity(rs.getInt("quantity"));
        // TODO put here CATEGORY
        //product.setCategory(rs.getObject("category_id"));

        return product;
    }

    @Override
    public Product makeUnique(Map<Long, Product> cache, Product product) {
        cache.putIfAbsent(product.getId(), product);
        return cache.get(product.getId());
    }
}

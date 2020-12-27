package ua.hubanov.model.dao.mapper;

import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.exceptions.ProductNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ObjectMapper<T> {
    T extractFromResultSet(ResultSet rs) throws SQLException, ProductNotFoundException, CartNotFoundException;

    T makeUnique(Map<Long, T> cache, T entity);
}

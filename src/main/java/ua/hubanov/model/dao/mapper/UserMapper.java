package ua.hubanov.model.dao.mapper;

import ua.hubanov.model.entity.Product;
import ua.hubanov.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
    // TODO add roles
        //    user.setRole(rs.getObject("role"));
        user.setNonLocked(rs.getBoolean("is_non_locked"));
    // TODO add cart in User then here
        //    user.setCart()

        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}

package ua.hubanov.model.dao.mapper;

import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.model.entity.Cart;
import ua.hubanov.model.entity.Product;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.entity.UserRole;
import ua.hubanov.model.service.CartService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    CartService cartService = new CartService();


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        user.setNonLocked(rs.getBoolean("is_non_locked"));
        user.setCart(new Cart(rs.getLong("cart_id")));

        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}

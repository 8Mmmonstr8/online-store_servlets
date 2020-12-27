package ua.hubanov.model.dao.mapper;

import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.model.entity.InCartProduct;
import ua.hubanov.model.service.CartService;
import ua.hubanov.model.service.ProductService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class InCartProductMapper implements ObjectMapper<InCartProduct> {
    CartService cartService = new CartService();
    ProductService productService = new ProductService();

    @Override
    public InCartProduct extractFromResultSet(ResultSet rs) throws SQLException, ProductNotFoundException, CartNotFoundException {
        InCartProduct inCartProduct = new InCartProduct();
        inCartProduct.setId(rs.getLong("id"));
        inCartProduct.setCart(cartService.findById(rs.getLong("cart_id")));
        inCartProduct.setProduct(productService.findById(rs.getLong("product_id")));
        inCartProduct.setNeededQuantity(rs.getInt("needed_quantity"));
        return inCartProduct;
    }

    @Override
    public InCartProduct makeUnique(Map<Long, InCartProduct> cache, InCartProduct inCartProduct) {
        cache.putIfAbsent(inCartProduct.getId(), inCartProduct);
        return cache.get(inCartProduct.getId());
    }
}

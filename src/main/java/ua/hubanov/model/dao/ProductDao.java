package ua.hubanov.model.dao;

import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {
    List<Category> findAllCategories();
    void createNewCategory(String catName);

    void updateProduct(Product product, Long productId);
}

package ua.hubanov.model.service;

import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.model.dao.DaoFactory;
import ua.hubanov.model.dao.ProductDao;
import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Product;

import java.util.List;

public class ProductService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Product findById(long id) throws ProductNotFoundException {
        try (ProductDao dao = daoFactory.createProductDao()) {
            return dao.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        }
    }

    public List<Product> getAllProducts() {
        try (ProductDao dao = daoFactory.createProductDao()) {
            return dao.findAll();
        }
    }

    public List<Category> getAllCategories() {
        try (ProductDao dao = daoFactory.createProductDao()) {
            return dao.findAllCategories();
        }
    }

    public void createNewCategory(String catName) {
        try (ProductDao dao = daoFactory.createProductDao()) {
            dao.createNewCategory(catName);
        }
    }

    public void createNewProduct(Product product) {
        try (ProductDao dao = daoFactory.createProductDao()) {
            dao.create(product);
        }
    }

    public void deleteProduct(Long productId) {
        try (ProductDao dao = daoFactory.createProductDao()) {
            dao.delete(productId);
        }
    }

    public void updateProduct(Product product, Long productId) {
        try (ProductDao dao = daoFactory.createProductDao()) {
            dao.updateProduct(product, productId);
        }
    }

    public List<Product> getAllProductsWithLimit(int currentPage, int recordsPerPage) {
        try (ProductDao dao = daoFactory.createProductDao()) {
            return dao.getAllProductWithLimit(currentPage, recordsPerPage);
        }
    }

    public int getNumberOfRows() {
        try (ProductDao dao = daoFactory.createProductDao()) {
            return dao.getNumberOfRows();
        }
    }
}

package ua.hubanov.model.service;

import ua.hubanov.model.dao.DaoFactory;
import ua.hubanov.model.dao.ProductDao;
import ua.hubanov.model.entity.Product;

import java.util.List;

public class ProductService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Product> getAllProducts() {
        try (ProductDao dao = daoFactory.createProductDao()) {
            return dao.findAll();
        }
    }

}

package ua.hubanov.model.dao.impl;

import ua.hubanov.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger LOGGER = LogManager.getLogger(JDBCDaoFactory.class);

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public ProductDao createProductDao() {
        return new JDBCProductDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public CartDao createCartDao() {
        return new JDBCCartDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    private Connection getConnection(){
        LOGGER.debug("connect");
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.info("connection error", e);
            throw new RuntimeException(e);
        }
    }
}

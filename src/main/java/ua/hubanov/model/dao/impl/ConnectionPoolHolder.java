package ua.hubanov.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolHolder.class);

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    ds.setUrl("jdbc:mysql://localhost:3306/online_store_servlets?useUnicode=true&serverTimezone=UTC");
                    ds.setUsername("root");
                    ds.setPassword("r1o2o3t4");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);

                    dataSource = ds;
                    LOGGER.info("connection pool created");
                }
            }
        }
        return dataSource;
    }
}

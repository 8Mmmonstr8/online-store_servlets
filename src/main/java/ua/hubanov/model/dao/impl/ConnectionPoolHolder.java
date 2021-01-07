package ua.hubanov.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPoolHolder {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolHolder.class);

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    ResourceBundle bundle = ResourceBundle.getBundle("database");
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(bundle.getString("db.driver"));
                    ds.setUrl(bundle.getString("db.url"));
                    ds.setUsername(bundle.getString("db.user"));
                    ds.setPassword(bundle.getString("db.password"));
                    ds.setMinIdle(Integer.parseInt(bundle.getString("db.minIdle")));
                    ds.setMaxIdle(Integer.parseInt(bundle.getString("db.maxIdle")));
                    ds.setMaxOpenPreparedStatements(Integer.parseInt(bundle.getString("db.maxOpenStatement")));

                    dataSource = ds;
                    LOGGER.info("connection pool created");
                }
            }
        }
        return dataSource;
    }
}

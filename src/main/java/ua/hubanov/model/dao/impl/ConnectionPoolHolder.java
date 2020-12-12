package ua.hubanov.model.dao.impl;

//import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    ds.setUrl("jdbc:mysql://localhost:3306/online_store?useUnicode=true&serverTimezone=UTC");
                    ds.setUsername("root");
                    ds.setPassword("r1o2o3t4");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
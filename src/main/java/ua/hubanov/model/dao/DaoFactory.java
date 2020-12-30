package ua.hubanov.model.dao;

import ua.hubanov.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ProductDao createProductDao();
    public abstract UserDao createUserDao();
    public abstract CartDao createCartDao();
    public abstract OrderDao createOrderDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}


package gl.mike.general;

import gl.mike.dao.BookDao;
import gl.mike.dao.UserDao;
import gl.mike.dao.impl.BookDaoImpl;
import gl.mike.dao.impl.UserDaoImpl;

/**
 * Created by user on 17.04.2016.
 */
public class FactoryDao {
    public static FactoryDao instance = new FactoryDao();
    public BookDao bookDao;
    public UserDao userDao;

    private FactoryDao() {}

    public static FactoryDao getInstance() {
        return FactoryDao.instance;
    }

    public BookDao getBookDao() {
        if(bookDao == null) {
            bookDao = new BookDaoImpl();
        }
        return bookDao;
    }

    public UserDao getUserDao() {
        if(userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }
}

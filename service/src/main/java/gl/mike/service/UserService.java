package gl.mike.service;


import gl.mike.dao.UserDao;
import gl.mike.general.FactoryDao;
import gl.mike.pojos.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Holub on 03.05.2015.
 */
public class UserService {
    private static Logger log = Logger.getLogger(UserService.class);
    private UserDao userDaoImpl = FactoryDao.getInstance().getUserDao();

    public User getUser(String login, String password) {
        List<User> users = null;
        try {
            users = userDaoImpl.getUser(login, password);
        } catch (SQLException e) {
            log.error("Unable find user:", e);
        }
        for (User user: users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return user;
            }
        }
       return null;
    }

}

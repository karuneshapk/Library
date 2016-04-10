package gl.mike.service;


import gl.mike.dao.UserDao;
import gl.mike.model.User;

/**
 * Created by Holub on 03.05.2015.
 */
public class UserService {
    private UserDao userDao = UserDao.getInstance();
    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User getUser(String login, String password) {
        return userDao.getUser(login, password);
    }

}

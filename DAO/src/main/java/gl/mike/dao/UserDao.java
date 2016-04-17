package gl.mike.dao;

import gl.mike.pojos.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 17.04.2016.
 */
public interface UserDao {
    public List<User> getUser(String login, String password) throws SQLException;
}

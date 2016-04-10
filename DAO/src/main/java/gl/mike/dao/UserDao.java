package gl.mike.dao;

import gl.mike.db.DataBaseConnection;
import gl.mike.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Holub on 03.05.2015.
 */
public class UserDao {
    //private static final String USER_EXIST_QUERY = "select * from user where login = ? and password = ?";
    public static final String QUERY = "select * from users where login = ? and password = ?";
    public static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public User getUser(String login, String password) {
        Connection conn = DataBaseConnection.getInstance().getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(QUERY);
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.executeQuery();
            ResultSet resultSet = stmt.getResultSet();
            resultSet.next();
            User user = new User();
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


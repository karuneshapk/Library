package gl.mike.dao.impl;

import gl.mike.dao.UserDao;
import gl.mike.util.HibernateUtil;
import gl.mike.pojos.User;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 17.04.2016.
 */
public class UserDaoImpl implements UserDao {

    List<User> users = null;
    @Override
    public List<User> getUser(String login, String password) throws SQLException {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(User.class).list();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }
}

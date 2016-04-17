package gl.mike.dao.impl;

import gl.mike.dao.UserDao;
import gl.mike.pojos.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

import static gl.mike.util.HibernateUtil.getHibernateUtil;

/**
 * Created by user on 17.04.2016.
 */
public class UserDaoImpl implements UserDao {
    private static Logger log = Logger.getLogger(UserDaoImpl.class);
    List<User> users = null;
    @Override
    public List<User> getUser(String login, String password) throws SQLException {
        Session session = null;
        try {
            session = getHibernateUtil().getSession();
            users = session.createCriteria(User.class).list();
            return users;
        } catch (Exception e) {
           log.error("Can't get users from db.", e);
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }
}

package gl.mike.dao.impl;

import gl.mike.dao.BookDao;
import gl.mike.pojos.Book;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

import static gl.mike.util.HibernateUtil.getHibernateUtil;

/**
 * Created by user on 17.04.2016.
 */
public class BookDaoImpl implements BookDao {
    private static Logger log = Logger.getLogger(BookDaoImpl.class);
    @Override
    public void addBook(Book book) throws SQLException {
        Session session = null;
        try {
            session = getHibernateUtil().getSession();
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Can't created session.", e);
        } finally {
            getHibernateUtil().closeSession(session);
        }
    }

    @Override
    public List<Book> getBooks() throws SQLException {
        List<Book> books = null;

        Session session = null;
        try {
            session = getHibernateUtil().getSession();
            books = session.createCriteria(Book.class).list();
        } catch (Exception e) {
            log.error("Can't get books from db.", e);
        } finally {
            getHibernateUtil().closeSession(session);
        }
        return books;
    }
}

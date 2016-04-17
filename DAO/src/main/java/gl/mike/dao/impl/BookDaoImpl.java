package gl.mike.dao.impl;

import gl.mike.dao.BookDao;
import gl.mike.pojos.Book;
import gl.mike.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 17.04.2016.
 */
public class BookDaoImpl implements BookDao {
    @Override
    public void addBook(Book book) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> getBooks() throws SQLException {
        List<Book> books = null;

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            books = session.createCriteria(Book.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return books;
    }
}

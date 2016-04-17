package gl.mike.service;

import gl.mike.dao.BookDao;
import gl.mike.general.FactoryDao;
import gl.mike.pojos.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Holub on 02.05.2015.
 */
public class BookService {
    private BookDao bookDaoImpl = FactoryDao.getInstance().getBookDao();

    public List<Book> getAllBooks() {
        try {
            return bookDaoImpl.getBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void saveOrUpdate(Book book) {
        try {
            bookDaoImpl.addBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

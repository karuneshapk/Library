package gl.mike.service;

import gl.mike.dao.BookDao;
import gl.mike.general.FactoryDao;
import gl.mike.pojos.Book;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Holub on 02.05.2015.
 */
public class BookService {
    private static Logger log = Logger.getLogger(BookService.class);
    private BookDao bookDaoImpl = FactoryDao.getInstance().getBookDao();

    public List<Book> getAllBooks() {
        try {
            return bookDaoImpl.getBooks();
        } catch (SQLException e) {
            log.error("No table with books.", e);
        }
        return null;
    }
    
    public void saveOrUpdate(Book book) {
        try {
            bookDaoImpl.addBook(book);
        } catch (SQLException e) {
            log.error("Can't add book to library.", e);
        }
    }
}

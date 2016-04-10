package gl.mike.service;

import gl.mike.dao.BookDao;
import gl.mike.model.Book;

import java.util.List;

/**
 * Created by Holub on 02.05.2015.
 */
public class BookService {
    private BookDao bookDao = BookDao.getInstance();
    private static BookService instance;

    private BookService() {
    }

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }
    
    public void saveOrUpdate(Book book) {
        bookDao.saveOrUpdate(book);
    }
}

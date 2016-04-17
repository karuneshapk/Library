package gl.mike.dao;

import gl.mike.pojos.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 17.04.2016.
 */
public interface BookDao {
    public void addBook(Book book) throws SQLException;
    public List<Book> getBooks() throws SQLException;
}

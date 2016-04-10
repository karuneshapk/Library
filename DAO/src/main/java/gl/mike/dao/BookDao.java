package gl.mike.dao;

import gl.mike.db.DataBaseConnection;
import gl.mike.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Holub on 02.05.2015.
 */
public class BookDao {
    private final static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BookDao.class);
    public static final String QUERY = "select * from books";
    public static final String QUERY_SAVE = "INSERT INTO books (name, author, description) VALUES (?,?,?)";
    public static BookDao instance;

    private BookDao() {
    }

    public static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }
        return instance;
    }

    public List<Book> getAll() {
        Connection conn = DataBaseConnection.getInstance().getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(QUERY);
            stmt.executeQuery();
            List<Book> books = new ArrayList<>();
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getString("description"),
                        resultSet.getString("author"),
                        resultSet.getString("name")
                );
                books.add(book);
            }

            return books;

        } catch (SQLException e) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, e); // TODO add log4j and log error
            return null;
        }
    }

    public void saveOrUpdate(Book book) {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        try {
            if (book.getId() == null) {
                // it's new book - save case
                PreparedStatement pstmt = conn.prepareStatement(QUERY_SAVE);

                pstmt.setString(1, book.getName());
                pstmt.setString(2, book.getAuthor());
                pstmt.setString(3, book.getDescription());

                pstmt.executeUpdate();
            } else {
                // update case, write update query
                // UPDATE query
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}

package gl.mike.servlets;

import gl.mike.controller.Action;
import gl.mike.controller.View;
import gl.mike.pojos.Book;
import gl.mike.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static gl.mike.Constants.*;

public class AddBooksAction implements Action {
    private static Logger log = Logger.getLogger(AddBooksAction.class);
    private BookService bookService = new BookService();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        bookService.saveOrUpdate(new Book(
                req.getParameter(BOOK_DESCRIPTION),
                req.getParameter(BOOK_AUTHOR),
                req.getParameter(BOOK_NAME)
        ));

        try {
            resp.sendRedirect(URL_SHOW_BOOKS_PAGE);
        } catch (IOException e) {
            log.error("Can't do redirect to showBooks.", e);
        }
        return null; // redirect to showBooks.jsp
    }
}

package gl.mike.servlets;

import gl.mike.controller.Action;
import gl.mike.controller.View;
import gl.mike.model.Book;
import gl.mike.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBooksAction implements Action {
    private BookService bookService = BookService.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        bookService.saveOrUpdate(new Book(
                req.getParameter("description"),
                req.getParameter("author"),
                req.getParameter("name")
        ));

        try {
            resp.sendRedirect("/servlets/showBooks");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // redirect to showBooks.jsp
    }
}

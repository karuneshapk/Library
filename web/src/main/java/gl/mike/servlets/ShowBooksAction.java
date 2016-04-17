package gl.mike.servlets;

import gl.mike.controller.Action;
import gl.mike.controller.View;
import gl.mike.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static gl.mike.Constants.*;

public class ShowBooksAction implements Action {
    private BookService bookService = new BookService();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> model = new HashMap<>();
        model.put(KEY_BOOKS, bookService.getAllBooks());

        return new View(KEY_SHOW_BOOKS, model); // showBooks.jsp
    }
}

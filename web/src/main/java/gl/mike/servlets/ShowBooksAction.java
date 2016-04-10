package gl.mike.servlets;

import gl.mike.controller.Action;
import gl.mike.controller.View;
import gl.mike.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class ShowBooksAction implements Action {
    private BookService bookService = BookService.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("books", bookService.getAllBooks());

        return new View("showBooks", model); // showBooks.jsp
    }
}

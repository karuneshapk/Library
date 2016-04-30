package gl.mike.servlets;

import gl.mike.controller.Action;
import gl.mike.controller.View;
import gl.mike.service.BookService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static gl.mike.Constants.KEY_BOOKS;
import static gl.mike.Constants.KEY_SHOW_BOOKS;

public class ShowBooksAction implements Action {
    private BookService bookService = new BookService();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        HashMap<String, Object> model = new HashMap<>();

        model.put(KEY_BOOKS, bookService.getAllBooks());
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userName")) {
                model.put("user", cookie.getValue());
            }
            if (cookie.getName().equals("status")) {
                model.put("status", cookie.getValue());
            }
        }
        return new View(KEY_SHOW_BOOKS, model); // showBooks.jsp
    }
}

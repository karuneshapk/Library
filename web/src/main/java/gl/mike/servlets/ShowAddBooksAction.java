package gl.mike.servlets;

import gl.mike.controller.Action;
import gl.mike.controller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static gl.mike.Constants.KEY_ADD_BOOKS;

/**
 * Created by Holub on 02.05.2015.
 */
public class ShowAddBooksAction implements Action {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        return new View(KEY_ADD_BOOKS);
    }
}

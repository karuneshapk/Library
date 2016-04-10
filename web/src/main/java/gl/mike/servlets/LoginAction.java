package gl.mike.servlets;

import gl.mike.controller.Action;
import gl.mike.controller.View;
import gl.mike.model.User;
import gl.mike.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Holub on 02.05.2015.
 */
public class LoginAction implements Action {
    //private static final Logger LOG = Logger.getLogger(LoginAction.class);

    public static final String AUTH_FLAG = "auth";
    private static final String DEFAULT_PAGE = "/servlets/showBooks";
    private UserService userService = UserService.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> model = new HashMap<>();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        ArrayList<String> errors = new ArrayList<>();
        if (login == null || password == null) {
            errors.add("Please define your username and password.");
            model.put("errors", errors);
            return new View("login", model);
        }

        User user = userService.getUser(login, password);
        if (user != null) {

            Cookie cookie = new Cookie(AUTH_FLAG, "1234"); // token > userID: ASASDASD21432423432ADSADS
            cookie.setMaxAge(Integer.MAX_VALUE);
            cookie.setVersion(0);
            cookie.setSecure(false);

            resp.addCookie(cookie); // add authentication
            try {
                resp.sendRedirect(DEFAULT_PAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            errors.add("Username or password are not correct. Please try again.");
        }

        model.put("errors", errors);
        return new View("login", model); // todo login with error
    }
}

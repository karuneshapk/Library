package gl.mike.servlets;

import gl.mike.controller.Action;
import gl.mike.controller.View;
import gl.mike.pojos.User;
import gl.mike.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static gl.mike.Constants.*;

/**
 * Created by Holub on 02.05.2015.
 */
public class LoginAction implements Action {
    private static final Logger log = Logger.getLogger(LoginAction.class);
    private UserService userService = new UserService();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> model = new HashMap<>();
        String login = req.getParameter(KEY_LOGIN);
        String password = req.getParameter(PASSWORD);

        ArrayList<String> errors = new ArrayList<>();
        if (login == null || password == null) {
            errors.add("Please define your username and password.");
            model.put(KEY_ERRORS, errors);
            return new View(KEY_LOGIN, model);
        }

        User user = userService.getUser(login, password);
        if (user != null) {

            Cookie cookie = new Cookie(AUTH_FLAG, "1234"); // token > userID: ASASDASD21432423432ADSADS
            cookie.setMaxAge(Integer.MAX_VALUE);
            cookie.setVersion(0);
            cookie.setSecure(false);

            resp.addCookie(cookie); // add authentication

            Cookie cookieLogin = new Cookie("userName", login);
            cookieLogin.setMaxAge(Integer.MAX_VALUE);
            cookieLogin.setVersion(0);
            cookieLogin.setSecure(false);

            resp.addCookie(cookieLogin);


            Cookie cookieStatus = new Cookie("status", user.getStatusName().getStatus());
            cookieStatus.setMaxAge(Integer.MAX_VALUE);
            cookieStatus.setVersion(0);
            cookieStatus.setSecure(false);

            resp.addCookie(cookieStatus);

            try {
                resp.sendRedirect(URL_SHOW_BOOKS_PAGE);
            } catch (IOException e) {
                log.error("Can't redirect to showBooks.", e);
            }
            return null;
        } else {
            errors.add("Username or password are not correct. Please try again.");
        }

        model.put(KEY_ERRORS, errors);
        return new View(KEY_LOGIN, model); // todo login with error
    }
}

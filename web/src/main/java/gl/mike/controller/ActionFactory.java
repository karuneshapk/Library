package gl.mike.controller;


import gl.mike.servlets.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static gl.mike.Constants.*;

public class ActionFactory {
    private static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put(KEY_ACTION_GET_LOGIN, new ShowLoginPageAction());
        actions.put(KEY_ACTION_POST_LOGIN, new LoginAction());
        actions.put(KEY_ACTION_GET_SHOW_BOOKS, new ShowBooksAction());
        actions.put(KEY_ACTION_GET_ADD_BOOKS, new ShowAddBooksAction());
        actions.put(KEY_ACTION_POST_ADD_BOOKS, new AddBooksAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}

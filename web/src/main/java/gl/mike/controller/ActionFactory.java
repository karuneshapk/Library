package gl.mike.controller;


import gl.mike.servlets.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("GET/login", new ShowLoginPageAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/showBooks", new ShowBooksAction());
        actions.put("GET/addBooks", new ShowAddBooksAction());
        actions.put("POST/addBooks", new AddBooksAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}

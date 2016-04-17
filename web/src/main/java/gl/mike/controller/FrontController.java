package gl.mike.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static gl.mike.Constants.*;

/**
 * Main controller
 */
public class FrontController extends HttpServlet {
    private final static Logger log = Logger.getLogger(FrontController.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = ActionFactory.getAction(req);

        if (action != null) {
            View view = action.execute(req, resp);
            if (view != null) {
                prepareModelData(req, view);
                dispatchRequestToView(view, req, resp);
            }
        } else {
            try {
                throw new SQLException();
            } catch (SQLException e) {
                log.error("Action = null.", e);
            }
        }
    }
// todo log error

    private void dispatchRequestToView(View view, HttpServletRequest request, HttpServletResponse response) {
        try {
            getServletContext().getRequestDispatcher(PREF_JSP + view.getName() + JSP).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Servlet Exception.", e);
        }
    }

    private void prepareModelData(HttpServletRequest request, View view) {
        final Map<String, Object> model = view.getModel();
        if (model != null) {
            for (Map.Entry<String, Object> data : model.entrySet()) {
                request.setAttribute(data.getKey(), data.getValue());
                log.trace("Model prepared.");
            }
        }
    }
}

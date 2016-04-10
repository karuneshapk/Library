package gl.mike.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Main controller
 */
public class FrontController extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(FrontController.class);

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
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
// todo log error

    private void dispatchRequestToView(View view, HttpServletRequest request, HttpServletResponse response) {
        try {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + view.getName() + ".jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareModelData(HttpServletRequest request, View view) {
        final Map<String, Object> model = view.getModel();
        if (model != null) {
            for (Map.Entry<String, Object> data : model.entrySet()) {
                request.setAttribute(data.getKey(), data.getValue());
            }
        }
    }
}

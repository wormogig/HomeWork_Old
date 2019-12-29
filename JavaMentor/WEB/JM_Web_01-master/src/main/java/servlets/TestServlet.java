package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] value = req.getParameterMap().get("value");
        try {
            int i = Integer.parseInt(value[0]);
            resp.getWriter().print(i * 2);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            resp.getWriter().println(0);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

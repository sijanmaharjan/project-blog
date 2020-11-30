package controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/blog.cache.id"})
public class ViewerIdCachingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewerId = req.getParameter("viewerId");
        if(viewerId != null){
            req.getSession().setAttribute("viewerId", viewerId);
            resp.setStatus(200);
        }
    }
}

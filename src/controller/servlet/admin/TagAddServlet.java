package controller.servlet.admin;

import dao.remote.BlogRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/blog.admin.addTag"})
public class TagAddServlet extends HttpServlet {

    @EJB
    private BlogRemote blogRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String tags = req.getParameter("tags");
        if(!tags.isEmpty()) {
            blogRemote.addTags(blogRemote.getBlog(id, false), tags.split(","));
        }
    }
}

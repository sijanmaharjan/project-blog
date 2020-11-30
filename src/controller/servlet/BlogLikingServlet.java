package controller.servlet;

import dao.remote.BlogRemote;
import model.Blog;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/blog.like"})
public class BlogLikingServlet extends HttpServlet {

    @EJB
    private BlogRemote blogRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogId = req.getParameter("blogId");
        String viewerId = req.getParameter("viewerId");
        if(blogId != null && viewerId != null){
            Blog blog = blogRemote.getBlog(blogId, false);
            if(blog != null) {
                blogRemote.addLike(blogId, viewerId);
            }
        }
    }
}
package controller.servlet.admin;

import dao.remote.BlogRemote;
import model.Blog;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/blog.admin.update"})
public class BlogUpdateServlet extends HttpServlet {

    @EJB
    private BlogRemote blogRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null) {
            Blog blog = new Blog();
            blog.setId(id);
            blog.setTitle(req.getParameter("title"));
            String sub = req.getParameter("subTitle");
            blog.setSubTitle(sub.isEmpty() ? null : sub);
            blog.setContent(req.getParameter("content"));
            blog.setCoverImage("test");
            blogRemote.updateBlog(blog);
            resp.setStatus(200);
        }
    }
}

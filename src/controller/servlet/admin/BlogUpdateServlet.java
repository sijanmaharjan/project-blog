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
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/blog.admin.update"})
public class BlogUpdateServlet extends HttpServlet {

    @EJB
    private BlogRemote blogRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null){
            Blog blog = blogRemote.getBlog(id, false);
            if(blog != null) {
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                out.print(blog);
                out.flush();
            }else resp.sendError(404);
        }else resp.sendError(400);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Blog blog = new Blog();
        blog.setId(req.getParameter("id"));
        blog.setTitle(req.getParameter("title"));
        String sub = req.getParameter("subTitle");
        blog.setSubTitle(sub.isEmpty()? null : sub);
        blog.setContent(req.getParameter("content"));
        blog.setCoverImage("test");
        blogRemote.updateBlog(blog);
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null){
            blogRemote.deleteBlog(id);
        }
    }
}

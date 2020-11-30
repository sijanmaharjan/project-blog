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

@WebServlet(urlPatterns = {"/blog.admin.upload"})
public class BlogUploadServlet extends HttpServlet {

    @EJB
    private BlogRemote blogRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Blog blog = new Blog();
        blog.setTitle(req.getParameter("title"));
        String sub = req.getParameter("subTitle");
        blog.setSubTitle(sub.isEmpty()? null : sub);
        blog.setContent(req.getParameter("content"));
        blog.setCoverImage("test");
        String tags = req.getParameter("tags").trim();
        if(tags.isEmpty()) {
            blogRemote.addNewBlog(blog);
        }else{
            blogRemote.addNewBlog(blog, tags.split(","));
        }
        resp.setStatus(200);
    }
}

package controller.servlet.admin;

import controller.service.ImageFileService;
import controller.service.ImageFileServiceImpl;
import dao.remote.BlogRemote;
import model.Blog;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/blog.admin.upload"})
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class BlogUploadServlet extends HttpServlet {

    @EJB
    private BlogRemote blogRemote;

    private ImageFileService imageFileService;

    @Override
    public void init() throws ServletException {
        imageFileService = new ImageFileServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Blog blog = new Blog();
        blog.setId(UUID.randomUUID().toString());
        blog.setTitle(req.getParameter("title"));
        String sub = req.getParameter("subTitle");
        blog.setSubTitle(sub.isEmpty()? null : sub);
        blog.setContent("");
        blog.setCoverImage(blog.getId()+".jpeg");
        String tags = req.getParameter("tags").trim();
        if(tags.isEmpty()) {
            blogRemote.addNewBlog(blog);
        }else{
            blogRemote.addNewBlog(blog, tags.split(","));
        }
        imageFileService.uploadFile(blog.getId(), req, req.getPart("coverImg"));
        resp.sendRedirect("blog.view?id="+blog.getId());
    }
}

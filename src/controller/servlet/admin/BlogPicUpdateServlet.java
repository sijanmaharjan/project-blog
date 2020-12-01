package controller.servlet.admin;

import controller.service.ImageFileService;
import controller.service.ImageFileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/blog.admin.update-pic"})
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class BlogPicUpdateServlet extends HttpServlet {

    private ImageFileService imageFileService;

    @Override
    public void init() throws ServletException {
        this.imageFileService = new ImageFileServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogId = req.getParameter("id");
        imageFileService.removeFile(req, blogId);
        imageFileService.uploadFile(blogId, req, req.getPart("coverImg"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("blog.view?id="+blogId);
    }
}

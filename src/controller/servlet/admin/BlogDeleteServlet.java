package controller.servlet.admin;

import controller.service.ImageFileService;
import controller.service.ImageFileServiceImpl;
import dao.remote.BlogRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/blog.admin.delete"})
public class BlogDeleteServlet extends HttpServlet {

    @EJB
    private BlogRemote blogRemote;
    private ImageFileService imageFileService;

    @Override
    public void init() throws ServletException {
        this.imageFileService = new ImageFileServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null){
            blogRemote.deleteBlog(id);
            imageFileService.removeFile(req, id);
        }
    }
}

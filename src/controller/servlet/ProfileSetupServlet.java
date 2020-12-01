package controller.servlet;

import controller.service.ImageFileService;
import controller.service.ImageFileServiceImpl;
import dao.remote.ProfileRemote;
import model.Profile;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/blog.set"})
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class ProfileSetupServlet extends HttpServlet {

    @EJB
    private ProfileRemote profileRemote;
    private ImageFileService imageFileService;

    @Override
    public void init() throws ServletException {
        this.imageFileService = new ImageFileServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!profileRemote.checkIfProfileExists()) {
            Profile profile = new Profile();
            String id = UUID.randomUUID().toString();
            profile.setPicture(id+".jpeg");
            profile.setFirstName(req.getParameter("firstName"));
            profile.setLastName(req.getParameter("lastName"));
            profile.setFacebookId(req.getParameter("facebookId"));
            profile.setInstagramId(req.getParameter("instagramId"));
            profile.setGithubId(req.getParameter("githubId"));
            profile.setBio(req.getParameter("bio"));
            profile.setEmail(req.getParameter("email"));
            profile.setUsername(req.getParameter("username"));
            profile.setPassword(BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt()));
            profileRemote.saveProfile(profile);
            imageFileService.uploadFile(id, req, req.getPart("profileImg"));
            req.getServletContext().setAttribute("profile", null);
            resp.sendRedirect("blog.jeni?p=b");
        }else{
            resp.sendError(403);
        }
    }
}

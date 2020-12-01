package controller.servlet.admin;

import dao.remote.ProfileRemote;
import model.Profile;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/blog.admin.profile"})
public class ProfileUpdateServlet extends HttpServlet {

    @EJB
    private ProfileRemote profileRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null) {
            Profile profile = new Profile();
            Profile old = (Profile) req.getServletContext().getAttribute("profile");
            profile.setId(Integer.parseInt(id));
            profile.setPicture(old.getPicture());
            profile.setFirstName(req.getParameter("firstName"));
            profile.setLastName(req.getParameter("lastName"));
            profile.setFacebookId(req.getParameter("facebookId"));
            profile.setInstagramId(req.getParameter("instagramId"));
            profile.setGithubId(req.getParameter("githubId"));
            profile.setBio(req.getParameter("bio"));
            profile.setEmail(req.getParameter("email"));
            profile.setUsername(req.getParameter("username"));
            if(!req.getParameter("password").trim().isEmpty()) {
                profile.setPassword(BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt()));
            }else{
                profile.setPassword(old.getPassword());
            }
            profileRemote.updateProfile(profile);
            req.getServletContext().setAttribute("profile", null);
            resp.setStatus(200);
        }else{
            resp.sendError(404);
        }
    }
}

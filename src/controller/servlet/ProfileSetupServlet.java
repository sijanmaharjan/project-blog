package controller.servlet;

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

@WebServlet(urlPatterns = {"/blog.set"})
public class ProfileSetupServlet extends HttpServlet {

    @EJB
    private ProfileRemote profileRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!profileRemote.checkIfProfileExists()) {
            Profile profile = new Profile();
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
            req.getServletContext().setAttribute("profile", null);
            resp.setStatus(200);
        }else{
            resp.sendError(403);
        }
    }
}

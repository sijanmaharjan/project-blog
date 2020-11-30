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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/blog.in"})
public class LoginServlet extends HttpServlet {

    @EJB
    private ProfileRemote profileRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Profile profile = profileRemote.getProfile();
        HttpSession session = req.getSession();
        if(profile != null && profile.getUsername().equals(username) && BCrypt.checkpw(password, profile.getPassword())){
            session.setAttribute("isLoggedIn", true);
        }else{
            session.setAttribute("isLoggedIn", false);
            resp.sendError(402, "Invalid username or password");
        }
    }
}

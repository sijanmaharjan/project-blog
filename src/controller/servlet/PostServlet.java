package controller.servlet;

import dao.remote.BlogRemote;
import model.Blog;
import model.Hashtag;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/blog.view"})
public class PostServlet extends HttpServlet {

    @EJB
    private BlogRemote blogRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.isNew() || session.getAttribute("isLoggedIn") == null) {
            session.setAttribute("isLoggedIn", false);
        }

        String id = req.getParameter("id");
        if(id == null) resp.sendRedirect("blog.jeni");
        Blog blog = blogRemote.getBlog(id, session.getAttribute("isLoggedIn") == null||!(Boolean) session.getAttribute("isLoggedIn"));
        if(blog == null) resp.sendRedirect("blog.jeni");
        List<Hashtag> tags = blogRemote.getTags(id);
        List<Blog> relatedBlogs = blogRemote.getRelatedBlogs(id, tags.stream().map(Hashtag::getTitle).collect(Collectors.toList()));
        List<Blog> popularBlogs = blogRemote.getPopularBlogs();
        List<Blog> highlyLikedBlogs = blogRemote.getHighlyLikedBlogs();
        List<Blog> randomBlogs = blogRemote.getRandomBlogs();

        req.setAttribute("blog", blog);
        req.setAttribute("tags", tags);
        req.setAttribute("relatedBlogs", relatedBlogs);
        req.setAttribute("popularBlogs", popularBlogs);
        req.setAttribute("highlyLikedBlogs", highlyLikedBlogs);
        req.setAttribute("randomBlogs", randomBlogs);
        req.setAttribute("zone", ZoneId.systemDefault());
        req.getRequestDispatcher("post.jsp").forward(req, resp);
    }
}

package controller.servlet;

import dao.ejb.BlogDao;
import dao.remote.BlogRemote;
import model.Blog;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {"/blog.jeni"})
public class HomeServlet extends HttpServlet {

    final static private String OFFSET = "offset";

    @EJB
    private BlogRemote blogRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serve = req.getParameter("serve");
        Cookie offsetCookie;
        try {
            offsetCookie = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals(OFFSET)).findFirst().orElse(new Cookie(OFFSET, "0"));
        }catch (NullPointerException e){
            offsetCookie = new Cookie(OFFSET, "0");
        }
        int offset = Integer.parseInt(offsetCookie.getValue());
        if(serve != null){
            switch (serve){
                case "old":
                    offset += BlogDao.LIST_LIMIT;
                    offsetCookie.setValue(String.valueOf(offset));
                    break;
                case "new":
                    if(offset > 0) {
                        offset -= BlogDao.LIST_LIMIT;
                        offsetCookie.setValue(String.valueOf(offset));
                    }
                    break;
            }
        }

        HttpSession session = req.getSession();
        if(session.isNew() || session.getAttribute("isLoggedIn") == null){
            session.setAttribute("isLoggedIn", false);
        }

        List<Blog> blogList = blogRemote.getList(offset);
        if(!blogList.isEmpty()) resp.addCookie(offsetCookie);
        List<Blog> popularBlogs = blogRemote.getPopularBlogs();
        List<Blog> highlyLikedBlogs = blogRemote.getHighlyLikedBlogs();
        List<Blog> randomBlogs = blogRemote.getRandomBlogs();
        req.setAttribute("offset", offset);
        req.setAttribute("blogs", blogList);
        req.setAttribute("popularBlogs", popularBlogs);
        req.setAttribute("highlyLikedBlogs", highlyLikedBlogs);
        req.setAttribute("randomBlogs", randomBlogs);
        req.setAttribute("zone", ZoneId.systemDefault());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}

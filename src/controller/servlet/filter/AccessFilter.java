package controller.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AccessFilter", urlPatterns = {"/*"}, dispatcherTypes = DispatcherType.REQUEST)
public class AccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        if(!uri.contains("blog.admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            HttpServletResponse res = (HttpServletResponse) servletResponse;
            HttpSession session = req.getSession();
            Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
            if(isLoggedIn == null || !isLoggedIn){
                res.setStatus(403);
                res.sendRedirect("blog.jeni");
            }else{
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}

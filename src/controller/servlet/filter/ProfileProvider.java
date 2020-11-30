package controller.servlet.filter;

import dao.remote.ProfileRemote;
import model.Profile;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "ProfileProvider", urlPatterns = {"/*"}, dispatcherTypes = DispatcherType.REQUEST)
public class ProfileProvider implements Filter{
    private ServletContext context;

    @EJB
    private ProfileRemote profileRemote;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        if (uri.contains("blog") && context.getAttribute("profile") == null) {
            Profile profile = profileRemote.getProfile();
            context.setAttribute("profile", profile == null ? new Profile() : profile);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

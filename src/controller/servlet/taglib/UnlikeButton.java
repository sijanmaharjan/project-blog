package controller.servlet.taglib;

import dao.remote.BlogRemote;

import javax.ejb.EJB;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class UnlikeButton extends SimpleTagSupport {
    private String blogId;
    private String viewerId;

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public void setViewerId(String viewerId) {
        this.viewerId = viewerId;
    }

    @EJB
    private BlogRemote blogRemote;

    @Override
    public void doTag() throws JspException, IOException {
        boolean likes = !viewerId.isEmpty() && blogRemote.checkLikes(blogId, viewerId);
        if(likes){
            JspWriter out = getJspContext().getOut();
            out.print("<button class=\"btn btn-primary\" style=\"padding: 2px 5px\" onclick=\"unlikeBlog('"+blogId+"', '"+viewerId+"')\"><i class=\"fa fa-thumbs-up\"></i>Unlike</button>");
        }
    }
}

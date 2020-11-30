package controller.servlet.taglib;

import dao.remote.BlogRemote;

import javax.ejb.EJB;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class LikeCount extends SimpleTagSupport {
    private String blogId;

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    @EJB
    private BlogRemote blogRemote;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        Long totalLikes = blogRemote.getLikeCount(blogId);
        out.print(totalLikes+" likes");
    }
}

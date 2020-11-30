<%@ page import="model.Blog" %>
<%
    Blog blog2 = (Blog) request.getAttribute("blog");
%>
<div class="form-group">
    <input type="text" class="form-control" id="title" name="title" placeholder="Title" value='<%=blog2 != null?blog2.getTitle():""%>' required>
</div>
<div class="form-group">
    <input type="text" class="form-control" id="subTitle" name="subTitle" value='<%=blog2 != null && blog2.getSubTitle() != null?blog2.getSubTitle():""%>' placeholder="Sub-Title">
</div>
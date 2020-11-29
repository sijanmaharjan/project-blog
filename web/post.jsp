<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="zone" scope="request" type="java.time.ZoneId"/>
<jsp:useBean id="blog" scope="request" type="model.Blog"/>
<jsp:useBean id="tags" scope="request" type="java.util.List<model.Hashtag>"/>
<jsp:useBean id="relatedBlogs" scope="request" type="java.util.List<model.Blog>"/>
<jsp:useBean id="popularBlogs" scope="request" type="java.util.List<model.Blog>"/>
<jsp:useBean id="highlyLikedBlogs" scope="request" type="java.util.List<model.Blog>"/>
<jsp:useBean id="randomBlogs" scope="request" type="java.util.List<model.Blog>"/>
<!DOCTYPE html>
<html lang="en">
  <%@include file="components/head.jsp"%>
  <body>
    <%@include file="components/nav.jsp"%>
    <%@include file="components/post/post.jsp"%>
    <hr>
    <%@include file="components/footer.jsp"%>
    <%@include file="components/scripts.jsp"%>
  </body>
</html>
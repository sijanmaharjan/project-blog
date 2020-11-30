<%@ taglib prefix="b" uri="/WEB-INF/blog.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="p" scope="request" type="java.lang.String"/>
<jsp:useBean id="zone" scope="request" type="java.time.ZoneId"/>
<jsp:useBean id="profile" scope="application" type="model.Profile"/>
<!DOCTYPE html>
<html lang="en">
  <%@include file="components/head.jsp"%>
  <body>
    <%@include file="components/nav.jsp"%>
    <c:choose>
      <c:when test='<%=p==null || p.equals("a")%>'>
        <jsp:useBean id="offset" scope="request" type="java.lang.Integer"/>
        <jsp:useBean id="blogs" scope="request" type="java.util.List<model.Blog>"/>
        <jsp:useBean id="popularBlogs" scope="request" type="java.util.List<model.Blog>"/>
        <jsp:useBean id="highlyLikedBlogs" scope="request" type="java.util.List<model.Blog>"/>
        <jsp:useBean id="randomBlogs" scope="request" type="java.util.List<model.Blog>"/>
        <%@include file="components/home/home.jsp"%>
      </c:when>
      <c:when test='<%="b".equals(p)%>'>
        <%@include file="components/about/about.jsp"%>
      </c:when>
    </c:choose>
    <hr>
    <%@include file="components/footer.jsp"%>
    <%@include file="components/modal.jsp"%>
    <%@include file="components/scripts.jsp"%>
  </body>
</html>

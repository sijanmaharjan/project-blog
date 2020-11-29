<div class="suggest col-lg-4 col-md-2">
<c:if test="${relatedBlogs != null && relatedBlogs.size() > 0}">
    Related Blogs
    <div class="col-lg-12 col-md-12 mx-auto">
        <c:forEach var="blog" items="${relatedBlogs}">
            <%@include file="suggest-item.jsp"%>
        </c:forEach>
    </div>
    <br/>
</c:if>
<c:if test="${popularBlogs.size() > 0}">
    Popular Blogs
    <div class="col-lg-12 col-md-12 mx-auto">
        <c:forEach var="blog" items="${popularBlogs}">
            <%@include file="suggest-item.jsp"%>
        </c:forEach>
    </div>
    <br/>
</c:if>
<c:if test="${highlyLikedBlogs.size() > 0}">
    Highly Liked Blogs
    <div class="col-lg-12 col-md-12 mx-auto">
        <c:forEach var="blog" items="${highlyLikedBlogs}">
            <%@include file="suggest-item.jsp"%>
        </c:forEach>
    </div>
</c:if>
</div>
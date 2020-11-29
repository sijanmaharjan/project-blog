<c:if test="${randomBlogs.size() > 0}">
    <br/>
    Randomly Selected Blogs
    <div class="col-lg-12 col-md-12 mx-auto" >
        <div class="row">
            <c:forEach var="blog" items="${randomBlogs}">
                <%@include file="suggest-item.jsp"%>
            </c:forEach>
        </div>
    </div>
</c:if>
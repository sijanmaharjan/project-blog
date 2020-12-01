<!-- Page Header -->
<c:choose>
    <c:when test="${randomBlogs.size() > 0}">
        <header class="masthead" style="<c:if test="${randomBlogs.get(0).coverImage != null}">background-image: url('images/${randomBlogs.get(0).coverImage}');</c:if> height: 40vh;">
            <div class="overlay" style="height: 40vh;"></div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-10 mx-auto">
                        <div class="site-heading" style="padding: 100px 0;">
                            <a href="blog.view?id=${randomBlogs.get(0).id}" style="color: white">
                                <h1 style="font-size: 24pt">${randomBlogs.get(0).title}</h1>
                                <span class="subheading">${randomBlogs.get(0).subTitle}</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </c:when>
    <c:otherwise>
        <div style="height: 80px; background-color: #1b1e21; margin-bottom: 20px"></div>
    </c:otherwise>
</c:choose>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <c:if test="${isLoggedIn}">
                <div style="height: 50px"><a class="btn btn-primary float-right" onclick="writeBlog()"><i class="fa fa-plus"></i> ADD NEW</a></div>
            </c:if>
            <c:forEach var="blog" items="${blogs}">
                <%@include file="../blog-item.jsp"%>
            </c:forEach>
            <!-- Pager -->
            <div class="clearfix">
                <c:if test="${blogs.size() > 0}">
                    <a class="btn btn-primary float-right" href="blog.jeni?serve=old">Older Posts &rarr;</a>
                </c:if>
                <c:if test="${offset > 0}">
                    <a class="btn btn-primary float-right" href="blog.jeni?serve=new">&larr; Newer Posts</a>
                </c:if>
            </div>
        </div>
        <%@include file="../suggest.jsp"%>
    </div>
    <%@include file="../random-suggest.jsp"%>
</div>

<c:if test="${isLoggedIn}">
    <%@include file="../post/new.jsp"%>
    <script>
        function writeBlog() {
            showGeneralModal("#new-blog-form")
        }
    </script>
</c:if>
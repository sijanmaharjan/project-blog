<!-- Page Header -->
<c:if test="${randomBlogs.size() > 0}">
    <header class="masthead" style="background-image: url('img/home-bg.jpg'); height: 40vh;">
        <div class="overlay" style="height: 40vh;"></div>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-10 mx-auto">
                    <div class="site-heading" style="padding: 100px 0;">
                        <a href="blog.view?id=${randomBlogs.get(0).id}" style="color: white">
                            <h1>${randomBlogs.get(0).title}</h1>
                            <span class="subheading">${randomBlogs.get(0).subTitle}</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </header>
</c:if>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <a class="btn btn-primary float-right" onclick="writeBlog()">+ ADD NEW</a>
            <c:forEach var="blog" items="${blogs}">
                <%@include file="../blog-item.jsp"%>
            </c:forEach>
            <!-- Pager -->
            <div class="clearfix">
                <c:if test="${offset > 0}">
                    <a class="btn btn-primary float-right" href="blog.jeni?serve=new">&larr; Newer Posts</a>
                </c:if>
                <c:if test="${blogs.size() > 0}">
                    <a class="btn btn-primary float-right" href="blog.jeni?serve=old">Older Posts &rarr;</a>
                </c:if>
            </div>
        </div>
        <%@include file="../suggest.jsp"%>
    </div>
    <%@include file="../random-suggest.jsp"%>
</div>

<%@include file="../post/new.jsp"%>

<script>
    function writeBlog() {
        showGeneralModal("#new-blog-form")
    }
</script>
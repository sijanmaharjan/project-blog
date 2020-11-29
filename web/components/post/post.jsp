<!-- Page Header -->
<header class="masthead" style="background-image: url('img/post-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="post-heading">
                    <h1>${blog.title}</h1>
                    <h2 class="subheading">${blog.subTitle}</h2>
                    <span class="meta">Posted on ${blog.timestamp.toInstant().atZone(zone).toLocalDateTime()}</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Post Content -->
<article>
    <c:if test="${tags.size() > 0}">
        <div class="container">
            <div class="row">
                <c:forEach var="tag" items="${tags}">
                    <div class="col-md-1">${tag.title}</div>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                ${blog.content}
            </div>
            <%@include file="../suggest.jsp"%>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <%@include file="../random-suggest.jsp"%>
        </div>
    </div>
</article>
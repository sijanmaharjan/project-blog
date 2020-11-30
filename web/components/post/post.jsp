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
                <c:if test="${isLoggedIn}">
                    <div class="col-md-12" style="height: 70px">
                        <a class="btn btn-primary" onclick="modifyBlog()">Make Change</a>
                        <a class="btn btn-danger" onclick="deleteBlog()">Remove</a>
                    </div>
                </c:if>
                <c:forEach var="tag" items="${tags}">
                    <a class="tag" style="
                        background-color: #212529;
                        border-radius: 10px;
                        padding: 3px 8px;
                        color:white;
                        font-size: 10pt;
                        cursor: default;
                        margin-left: 10px;
                    ">#${tag.title}</a>
                </c:forEach>
            </div>
        </div>
        <br/>
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

<c:if test="${isLoggedIn}">
    <%@include file="../post/update.jsp"%>
    <script>
        function modifyBlog() {
            const el = $('#update-blog-form');
            $(el).find("textarea#content").text('${blog.content}');
            $(el).find("input#tags").removeAttr("required");
            $(el).find("input#tags").attr("hidden", "hidden");
            showGeneralModal("#update-blog-form");
        }
        function deleteBlog() {
            const resp = confirm("Are you sure to delete this blog?");
            if(resp){
                $.post(
                    'blog.admin.delete',
                    {
                        id: '${blog.id}'
                    },
                    function(data){
                        window.location = "blog.jeni"
                    }
                ).fail(handleRequestFailure)
            }
        }
    </script>
</c:if>
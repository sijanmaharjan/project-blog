<%@ page import="java.util.Optional" %>
<% String viewerId = Optional.ofNullable((String) session.getAttribute("viewerId")).orElse("");%>

<!-- Page Header -->
<header class="masthead" style="background-image: url('img/post-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="post-heading">
                    <h1>${blog.title}</h1>
                    <h2 class="subheading">${blog.subTitle}</h2>
                    <span class="meta"><b:LikeButton blogId="${blog.id}" viewerId="<%=viewerId%>"/><b:UnlikeButton blogId="${blog.id}" viewerId="<%=viewerId%>"/> ${blog.viewCount} views, <b:LikeCount blogId="${blog.id}"/>, Posted on ${blog.timestamp.toInstant().atZone(zone).toLocalDateTime()}</span>
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
                <c:if test="${!isLoggedIn}">
                    ${blog.content}
                </c:if>
                <c:if test="${isLoggedIn}">
                    <textarea id="edit-content">${blog.content}</textarea>
                    <br/>
                    <span class="float-right">
                        <a class="btn btn-danger" onclick="deleteBlog()"><i class="fa fa-trash"></i> Delete Blog</a>
                        <a class="btn btn-primary" onclick="updateBlogContent()"><i class="fa fa-save"></i> Save Changes</a>
                    </span>
                </c:if>
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
    <script>
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

        tinymce.init({
            selector: "textarea#edit-content",
            height: 600
        });

        function updateBlogContent() {
            $.post(
                'blog.admin.update',
                {
                    id: '${blog.id}',
                    title: '${blog.title}',
                    subTitle: '${blog.subTitle}',
                    content: tinymce.get('edit-content').getContent()
                },
                function (data) {
                    location.reload();
                }
            ).fail(handleRequestFailure);
        }
    </script>
</c:if>
<script>
    function likeBlog(blogId, viewer) {
        if(viewer === ""){
            let id = prompt("Enter Your Email");
            if(/^[a-z0-9A_Z.]+@[a-z]+.[a-z]+$/.test(id)){
                $.post(
                    'blog.cache.id',
                    {
                        viewerId: id
                    },
                    function (data) {
                        location.reload();
                    }
                ).fail(handleRequestFailure)
            }else{
                alert("invalid email!");
            }
        }else{
            sendLike(blogId, viewer)
        }
    }
    function sendLike(blogId, viewerId){
        $.post(
            'blog.like',
            {
                blogId: blogId,
                viewerId: viewerId
            },
            function (data) {
                location.reload();
            }
        ).fail(handleRequestFailure)
    }
    function unlikeBlog(blogId, viewer) {
        if(viewer === ""){
            let id = prompt("Enter Your Email");
            if(/^[a-z0-9A_Z.]+@[a-z]+.[a-z]+$/.test(id)){
                $.post(
                    'blog.cache.id',
                    {
                        viewerId: id
                    },
                    function (data) {
                        location.reload();
                    }
                ).fail(handleRequestFailure)
            }else{
                alert("invalid email!");
            }
        }else{
            sendUnlike(blogId, viewer)
        }
    }
    function sendUnlike(blogId, viewerId){
        $.post(
            'blog.unlike',
            {
                blogId: blogId,
                viewerId: viewerId
            },
            function (data) {
                location.reload();
            }
        ).fail(handleRequestFailure)
    }
</script>
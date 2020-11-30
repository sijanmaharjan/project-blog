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
                    <h2 class="subheading"><c:if test="${isLoggedIn}"><i class="fa fa-pen cursor-pointer" onclick="showUpdateBlogModal()"></i></c:if> ${blog.subTitle}</h2>
                    <span class="meta"><c:if test="${!isLoggedIn}"><b:LikeButton blogId="${blog.id}" viewerId="<%=viewerId%>"/><b:UnlikeButton blogId="${blog.id}" viewerId="<%=viewerId%>"/></c:if> ${blog.viewCount} views, <b:LikeCount blogId="${blog.id}"/>, Posted on ${blog.timestamp.toInstant().atZone(zone).toLocalDateTime()}</span>
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
                    ">#${tag.title}<c:if test="${isLoggedIn}"> <i class="fa fa-times-circle cursor-pointer" onclick="removeTag(${tag.id})"></i></c:if></a>
                </c:forEach>
                <c:if test="${isLoggedIn}">
                    <a class="tag" style="
                            background-color: #212529;
                            border-radius: 10px;
                            padding: 3px 8px;
                            color:white;
                            font-size: 10pt;
                            cursor: pointer;
                            margin-left: 10px;
                        " onclick="addTag()"><i class="fa fa-plus"></i> add tag</a>
                </c:if>
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
                <div id="disqus_thread"></div>
                <script>
                    /**
                     *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
                     *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables    */

                    var disqus_config = function () {
                    this.page.url = window.location.href;  // Replace PAGE_URL with your page's canonical URL variable
                    this.page.identifier = "${blog.id}"; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
                    };

                    (function() { // DON'T EDIT BELOW THIS LINE
                        var d = document, s = d.createElement('script');
                        s.src = 'https://project-blog-4.disqus.com/embed.js';
                        s.setAttribute('data-timestamp', +new Date());
                        (d.head || d.body).appendChild(s);
                    })();
                </script>
                <noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
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
    <%@include file="update.jsp"%>
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
            height: 600,
            menubar: false,
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

        function addTag(){
            const tags = prompt("Enter Tag Names to add: (separate by comma)");
            if(tags !== "" && /^[a-zA-Z,_ ]*$/.test(tags)){
                $.post(
                    "blog.admin.addTag",
                    {
                        id: '${blog.id}',
                        tags: tags
                    },
                    function (data) {
                        location.reload()
                    }
                ).fail(handleRequestFailure)
            }
        }

        function removeTag(id){
            $.post(
                "blog.admin.removeTag",
                {
                    id: id,
                    blogId: '${blog.id}'
                },
                function (data) {
                    location.reload()
                }
            ).fail(handleRequestFailure)
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
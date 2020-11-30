<div class="post-preview">
    <a href="blog.view?id=${blog.id}">
        <h2 class="post-title">
            ${blog.title}
        </h2>
        <c:if test="${blog.subTitle != null}">
            <em class="post-subtitle">
                ${blog.subTitle}
            </em>
        </c:if>
    </a>
    <p class="post-meta">${blog.viewCount} views, <b:LikeCount blogId="${blog.id}"/>, Posted on ${blog.timestamp.toInstant().atZone(zone).toLocalDateTime()}</p>
</div>
<hr>

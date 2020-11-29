<div class="post-preview">
    <a href="blog.view?id=${blog.id}">
        <h2 class="post-title" style="font-size: 18px">
            ${blog.title}
        </h2>
    </a>
    <p class="post-meta" style="font-size: 13px; margin: 5px 0">Posted on ${blog.timestamp.toInstant().atZone(zone).toLocalDateTime()}</p>
</div>
<hr>

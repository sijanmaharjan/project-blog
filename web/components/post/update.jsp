<div id="update-blog-form"hidden>
    <div class="modal-header border-bottom-0">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">x</span>
        </button>
    </div>
    <div class="modal-body">
        <div class="form-title text-center">
            <h4>Modify Blog</h4>
        </div>
        <div class="d-flex flex-column text-center">
            <form onsubmit="updateBlog(event, this)">
                <input type="text" name="id" value="${blog.id}" hidden>
                <%@include file="form.jsp"%>
                <button type="submit" class="btn btn-info btn-block btn-round">Update Changes</button>
            </form>
        </div>
    </div>
</div>
<script>
    function updateBlog(event, form) {
        $.post(
            'blog.admin.update',
            {
                id: $(form).find("#id").val(),
                title: $(form).find("#title").val(),
                subTitle: $(form).find("#subTitle").val(),
                content: $(form).find("#content").val(),
                tags: $(form).find("#tags").val()
            },
            function (data) {
                location.reload();
            }
        ).fail(handleRequestFailure);
        event.preventDefault();
    }
</script>
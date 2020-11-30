<div id="update-blog-form" hidden>
    <div class="modal-header border-bottom-0">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"><i class="fa fa-times"></i></span>
        </button>
    </div>
    <div class="modal-body">
        <div class="form-title text-center">
            <h4>Update Blog Title</h4>
        </div>
        <div class="d-flex flex-column text-center">
            <form onsubmit="updateBlog(event, this)">
                <%@include file="form.jsp"%>
                <button type="submit" class="btn btn-info btn-block btn-round">UPDATE</button>
            </form>
        </div>
    </div>
</div>
<script>
    function showUpdateBlogModal(){
        showGeneralModal("#update-blog-form");
    }
    function updateBlog(event, form) {
        $.post(
            'blog.admin.update',
            {
                id: '${blog.id}',
                title: $(form).find("#title").val(),
                subTitle: $(form).find("#subTitle").val()
            },
            function (data) {
                location.reload()
            }
        ).fail(handleRequestFailure);
        event.preventDefault();
    }
</script>
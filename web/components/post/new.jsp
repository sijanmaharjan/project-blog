<div id="new-blog-form"hidden>
    <div class="modal-header border-bottom-0">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"><i class="fa fa-times"></i></span>
        </button>
    </div>
    <div class="modal-body">
        <div class="form-title text-center">
            <h4>Write New Blog</h4>
        </div>
        <div class="d-flex flex-column text-center">
            <form method="post" action="blog.admin.upload" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="file" class="form-control" id="coverImg" name="coverImg" placeholder="Select Cover Image" accept="image/jpeg"/>
                </div>
                <%@include file="form.jsp"%>
                <div class="form-group">
                    <input type="text" class="form-control" id="tags" name="tags" placeholder="Tags (separate with comma)" pattern="^[a-zA-Z,_ ]*$">
                </div>
                <button type="submit" class="btn btn-info btn-block btn-round">ADD</button>
            </form>
        </div>
    </div>
</div>
<%--<script>--%>
<%--    function uploadBlog(event, form) {--%>
<%--        $.post(--%>
<%--            'blog.admin.upload',--%>
<%--            {--%>
<%--                title: $(form).find("#title").val(),--%>
<%--                subTitle: $(form).find("#subTitle").val(),--%>
<%--                tags: $(form).find("#tags").val()--%>
<%--            },--%>
<%--            function (data) {--%>
<%--                window.location="blog.view?id="+data;--%>
<%--            }--%>
<%--        ).fail(handleRequestFailure);--%>
<%--        event.preventDefault();--%>
<%--    }--%>
<%--</script>--%>
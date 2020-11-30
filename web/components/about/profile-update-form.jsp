<div id="profile-update-form" hidden>
    <div class="modal-header border-bottom-0">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"><i class="fa fa-times"></i></span>
        </button>
    </div>
    <div class="modal-body">
        <div class="form-title text-center">
            <h4>Update Profile</h4>
        </div>
        <div class="d-flex flex-column text-center">
            <form onsubmit="updateProfile(event, this)">
                <input type="number" id="id" name="id" value="${profile.id}" hidden/>
                <%@include file="../login/profile-form.jsp"%>
                <button type="submit" class="btn btn-info btn-block btn-round">Save Changes</button>
            </form>
        </div>
    </div>
</div>

<script>
    function updateProfile(event, form) {
        const psw = $(form).find("#password").val();
        const confirm = $(form).find("#confirm").val();
        if(psw === confirm) {
            $.post(
                'blog.admin.profile',
                {
                    id: $(form).find("#id").val(),
                    firstName: $(form).find("#firstName").val(),
                    lastName: $(form).find("#lastName").val(),
                    facebookId: $(form).find("#facebookId").val(),
                    instagramId: $(form).find("#instagramId").val(),
                    githubId: $(form).find("#githubId").val(),
                    bio: $(form).find("#bio").val(),
                    email: $(form).find("#email").val(),
                    username: $(form).find("#username").val(),
                    password: psw,
                },function (data) {
                    alert("Profile Updated!")
                    location.reload();
                    if(psw.trim().length>0){
                        window.location = "blog.out";
                    }
                }
            ).fail(handleRequestFailure)
        }else alert("cannot confirm password");
        event.preventDefault();
    }
    function showUpdateProfileModal(){
        showGeneralModal("#profile-update-form")
    }
</script>
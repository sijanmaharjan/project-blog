<div id="setup-modal-content" hidden>
    <div class="modal-header border-bottom-0">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">x</span>
        </button>
    </div>
    <div class="modal-body">
        <div class="form-title text-center">
            <h4>Setup Profile</h4>
        </div>
        <div class="d-flex flex-column text-center">
            <form onsubmit="setupProfile(event, this)">
                <%@include file="profile-form.jsp"%>
                <button type="submit" class="btn btn-info btn-block btn-round">Save</button>
            </form>
        </div>
    </div>
</div>

<script>
    function setupProfile(event, form) {
        const psw = $(form).find("#password").val();
        const confirm = $(form).find("#confirm").val();
        if(psw.trim().length > 0 && psw === confirm) {
            $.post(
                'blog.set',
                {
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
                    alert("Profile Setup Success!")
                    $("#general-modal").find("div.modal-content").html($('#login-modal-content').html());
                }
            ).fail(error=>{
                switch (error.status) {
                    case 403:
                        alert("Profile already exists!");
                        location.reload();
                        break;
                    default:
                        handleRequestFailure(error)
                }
            })
        }else alert("cannot confirm password");
        event.preventDefault();
    }
    function showSetupModal(){
        $("#general-modal").find("div.modal-content").html($('#setup-modal-content').html());
    }
</script>
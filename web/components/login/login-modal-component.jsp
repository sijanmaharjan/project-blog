<div id="login-modal-content" hidden>
    <div class="modal-header border-bottom-0">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"><i class="fa fa-times"></i></span>
        </button>
    </div>
    <div class="modal-body">
        <div class="form-title text-center">
            <h4>Login</h4>
        </div>
        <div class="d-flex flex-column text-center">
            <form onsubmit="login(event, this)">
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-info btn-block btn-round">Login</button>
            </form>
        </div>
    </div>
    <c:if test="${profile==null || profile.firstName == null || profile.firstName.trim().length() == 0}">
        <div class="modal-footer d-flex justify-content-center">
            <div class="signup-section">
                <a onclick="showSetupModal()" class="text-info cursor-pointer">Setup Profile</a>
            </div>
        </div>
    </c:if>
</div>

<script>
    function login(event, form) {
        $.post(
            'blog.in',{
                username: $(form).find('#username').val(),
                password: $(form).find('#password').val()
            },function(data){
                $('#login-nav-item').text("Logout");
                $('#login-nav-item').attr("onclick","logout()");
                hideGeneralModal();
                location.reload();
            }
        ).fail(handleRequestFailure);
        event.preventDefault();
    }
    function showLoginModal(){
        showGeneralModal("#login-modal-content")
    }
</script>
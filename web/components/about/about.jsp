<c:if test="${profile!=null}">
    <header class="masthead" style="<c:if test="${profile.picture != null}">background-image: url('images/${profile.picture}');</c:if>">
        <div class="overlay"></div>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-10 mx-auto">
                    <div class="site-heading" style="padding: 150px 0;">
                        <h1>${profile.firstName.toUpperCase()} ${profile.lastName.toUpperCase()}</h1>
                        <a style="color: white" href="mailto:${profile.email}"><span class="subheading">${profile.email}</span></a>
                        <c:if test="${isLoggedIn}">
                            <br/>
                            <span style="
                            color: white;
                            background-color: #040505;
                            border-radius: 20px;
                            font-size: 10pt;
                            padding: 3px 8px;
                            opacity: 0.7;
                            cursor:pointer;
                        " onclick="updateProfilePicture();"><i class="fa fa-camera"></i> Upload Profile Picture</span>
                            <form id="pp-update-form" method="post" action="blog.admin.update-pp" enctype="multipart/form-data" hidden>
                                <input type="text" name="picId" hidden value="${profile.picture}"/>
                                <input type="file" name="profileImg" required/>
                            </form><br/>
                            <a style="color: white"  onclick="showUpdateProfileModal()"><i class="fa fa-pen cursor-pointer"></i></a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <div class="container" style="align-items: center;">
        <div class="row">
            <div class="col-md-2">&nbsp;</div>
            <div class="col-md-8" style="text-align: center" >
                <em>${profile.bio}</em>
            </div>
        </div>
    </div>

</c:if>

<c:if test="${isLoggedIn}">
    <%@include file="profile-update-form.jsp"%>
    <script>
        function updateProfilePicture() {
            const form = $("#pp-update-form");
            $(form).find("input[name=profileImg]").click();
            $(form).find("input[name=profileImg]").change(function (){
                $(form).submit();
            });
        }
    </script>
</c:if>
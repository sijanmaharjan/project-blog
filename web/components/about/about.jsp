<c:if test="${profile!=null}">
    <header class="masthead" style="background-image: url('img/home-bg.jpg');">
        <div class="overlay"></div>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-10 mx-auto">
                    <div class="site-heading" style="padding: 150px 0;">
                        <h1>${profile.firstName.toUpperCase()} ${profile.lastName.toUpperCase()}</h1>
                        <a style="color: white" href="mailto:${profile.email}"><span class="subheading">${profile.email}</span></a>
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
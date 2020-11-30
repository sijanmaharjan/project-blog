<jsp:useBean id="isLoggedIn" scope="session" type="java.lang.Boolean"/>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="blog.jeni">Jenisha's Blog</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="blog.jeni">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="about.html">About</a>
                </li>
                <li class="nav-item">
                    <a id="login-nav-item" class="nav-link cursor-pointer" onclick="${isLoggedIn?"logout()":"showLoginModal()"}">${isLoggedIn?"Logout":"Login"}</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<%@include file="login/login-modal-component.jsp"%>
<%@include file="login/setup-modal-component.jsp"%>
<script>
    function logout() {
        window.location="blog.out";
    }
</script>
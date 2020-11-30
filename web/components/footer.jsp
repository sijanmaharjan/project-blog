<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <ul class="list-inline text-center">
                    <c:if test="${profile != null && profile.facebookId != null && profile.facebookId.trim().length() > 0 }">
                        <li class="list-inline-item">
                            <a href="https://www.facebook.com/${profile.facebookId}" target="_blank">
                                <span class="fa-stack fa-lg">
                                  <i class="fas fa-circle fa-stack-2x"></i>
                                  <i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${profile != null && profile.instagramId != null && profile.instagramId.trim().length() > 0 }">
                        <li class="list-inline-item">
                            <a href="https://www.instagram.com/${profile.instagramId}" target="_blank">
                            <span class="fa-stack fa-lg">
                              <i class="fas fa-circle fa-stack-2x"></i>
                              <i class="fab fa-instagram fa-stack-1x fa-inverse"></i>
                            </span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${profile != null && profile.githubId != null && profile.githubId.trim().length() > 0 }">
                        <li class="list-inline-item">
                            <a href="https://github.com/${profile.githubId}" target="_blank">
                                <span class="fa-stack fa-lg">
                                  <i class="fas fa-circle fa-stack-2x"></i>
                                  <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                    </c:if>
                </ul>
                <p class="copyright text-muted">Copyright &copy; Jenisha Maharjan 2020</p>
            </div>
        </div>
    </div>
</footer>
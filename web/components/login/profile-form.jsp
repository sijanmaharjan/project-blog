<div class="form-group">
    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" value='<%=profile != null && profile.getFirstName() != null? profile.getFirstName() : ""%>' required>
</div>
<div class="form-group">
    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" value='<%=profile != null && profile.getLastName() != null? profile.getLastName() : ""%>' required>
</div>
<div class="form-group">
    <input type="text" class="form-control" id="facebookId" name="facebookId" value='<%=profile != null && profile.getFacebookId() != null? profile.getFacebookId() : ""%>' placeholder="Facebook Id">
</div>
<div class="form-group">
    <input type="text" class="form-control" id="instagramId" name="instagramId" value='<%=profile != null && profile.getInstagramId() != null? profile.getInstagramId() : ""%>' placeholder="Instagram Id">
</div>
<div class="form-group">
    <input type="text" class="form-control" id="githubId" name="githubId" value='<%=profile != null && profile.getGithubId() != null? profile.getGithubId() : ""%>' placeholder="Github Id">
</div>
<div class="form-group">
    <input type="email" class="form-control" id="email" name="email" value='<%=profile != null && profile.getEmail() != null? profile.getEmail() : ""%>' placeholder="Email Address" required>
</div>
<div class="form-group">
    <input type="text" class="form-control" id="username" name="username" value='<%=profile != null && profile.getUsername() != null? profile.getUsername() : ""%>' placeholder="Username" required>
</div>
<div class="form-group">
    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
</div>
<div class="form-group">
    <input type="password" class="form-control" id="confirm" name="confirm" placeholder="Confirm Password">
</div>
<div class="form-group">
    <label for="bio">Write Short Bio:</label><br/>
    <textarea class="form-control" id="bio" name="bio" required><%=profile != null && profile.getBio() != null? profile.getBio() : ""%></textarea>
</div>
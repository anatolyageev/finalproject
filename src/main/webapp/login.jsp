<%@ include file="/WEB-INF/jspf/headLogin.jsp" %>
<c:set var="currentLocale" value="en" scope="session"/>
<body>
<%--===========================================================================
Defines the web form.
===========================================================================--%>
<fmt:message key="login.login_form.login" var="login"/>
<fmt:message key="login.login_form.password" var="password"/>
<fmt:message key="login.registration" var="registration"/>
<fmt:message key="login.login_form.button_login" var="loginButton"/>
<fmt:message key="registration.registration_form.fail" var="reg_fail"/>

<div class="hero">
    <div class="form-box">
        <div class="button-box">
            <div id="btn"></div>
            <button type="button" class="toggle-btn" onclick="login()">${loginButton}</button>
            <button type="button" class="toggle-btn" onclick="register()">${registration}</button>
        </div>
        <form id="login" action="controller" class="input-group" method="post">
            <input type="hidden" name="command" value="login"/>
            <input type="text" class="input-field" name="login" style="color:#999999" placeholder="User Id" required>
            <input type="password" class="input-field" name="password" placeholder="Enter Password"
                   style="color:#999999" required>
            <button type="submit" class="submit-btn">${loginButton}</button>
        </form>
        <form id="register" action="controller" class="input-group" method="post">
            <input type="hidden" name="command" value="registration"/>
            <input type="text" class="input-field" name="login" style="color:#999999" placeholder="User Id" required>
            <input type="text" class="input-field" name="name" style="color:#999999" placeholder="Name" required>
            <input type="text" class="input-field" name="last_name" style="color:#999999" placeholder="Last Name"
                   required>
            <input type="password" class="input-field" name="password" style="color:#999999"
                   placeholder="Enter Password" required>
            <button type="submit" class="submit-btn">Register</button>
        </form>
        <c:if test="${not empty reg_error}">
            <script> register()</script>
            <script> register()</script>
            <div class="error">
                    ${reg_fail}: ${reg_error}
            </div>
        </c:if>
    </div>
</div>

<script>
    var x = document.getElementById("login");
    var y = document.getElementById("register");
    var z = document.getElementById("btn");

    function register() {
        x.style.left = "-400px";
        y.style.left = "50px";
        z.style.left = "110px";
    }

    function login() {
        x.style.left = "50px";
        y.style.left = "450px";
        z.style.left = "0";
    }
</script>
</body>
</html>
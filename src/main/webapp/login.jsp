<%@ include file="/WEB-INF/jspf/headLogin.jsp" %>
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
<%--        <div class="sosial-icons">--%>
<%--            <img src="img\fb.png">--%>
<%--            <img src="img\tw.png">--%>
<%--            <img src="img\gp.png">--%>

<%--        </div>--%>
        <form id="login" action="controller" class="input-group" method="post">
            <input type="hidden" name="command" value="login"/>
            <input type="text" class="input-field" name="login" placeholder="User Id" required>
            <input type="password" class="input-field" name="password" placeholder="Enter Password" required>
<%--            <input type="checkbox" class="check-box"><span>Remember Password</span>--%>
            <button type="submit" class="submit-btn">${loginButton}</button>
        </form>
        <form id="register" action="controller"  class="input-group" method="post">
            <input type="hidden" name="command" value="registration"/>
            <input type="text" class="input-field" name="login" placeholder="User Id" required>
            <input type="text" class="input-field" name="name" placeholder="Name" required>
            <input type="text" class="input-field" name="last_name" placeholder="Last Name" required>
            <input type="password" class="input-field" name="password" placeholder="Enter Password" required>
<%--            <input type="checkbox" class="check-box"><span>I agree to the terms and conditions</span>--%>
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



<%--<c:if test="${not empty reg_error}">--%>
<%--    <div class="error">--%>
<%--        <fmt:message key="registration.registration_form.fail" var="reg_fail"/>--%>
<%--            ${reg_fail}: ${reg_error}--%>
<%--    </div>--%>
<%--</c:if>--%>


<script>
    var x = document.getElementById("login");
    var y = document.getElementById("register");
    var z = document.getElementById("btn");
    function register(){
        x.style.left = "-400px";
        y.style.left = "50px";
        z.style.left = "110px";
    }

    function login(){
        x.style.left = "50px";
        y.style.left = "450px";
        z.style.left = "0";
    }
</script>
</body>
</html>
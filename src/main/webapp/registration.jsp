<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 3/28/2020
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<fmt:message key="registration.header" var="page_name"/>
<fmt:message key="registration.registration_form.first_name" var="name"/>
<fmt:message key="registration.registration_form.last_name" var="last_name"/>
<fmt:message key="registration.registration_form.login" var="login"/>
<fmt:message key="registration.registration_form.password" var="password"/>

<%--<form id="login_form" method="post" action="/quiz/controller?command=login">--%>
<form id="registration_form" action="controller" method="post">

    <%--===========================================================================
    Hidden field. In the query it will act as command=login.
    The purpose of this to define the command name, which have to be executed
    after you submit current form.
    ===========================================================================--%>
    <input type="hidden" name="command" value="registration"/>

        <div class="form-label-group">
            <input type="text" id="inputName" class="form-control" placeholder="${name}" required autofocus>
            <label for="inputName">${name}</label>
        </div>

     ${login}: <input type="text" name="login"/> <br/>
        ${password}: <input type="password" name="password"/><br/>

    <input type="submit" value="${login}"/>
</form>
<form id="registration_form" action="controller" method="post">
    <input type="hidden" name="command" value="registration"/>
    <input type="submit" value="<fmt:message key="login.registration"/>"/>
</form>





<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>


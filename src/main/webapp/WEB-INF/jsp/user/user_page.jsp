<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 3/22/2020
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%--    <div class="row">--%>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerLeanguage.jsp" %>
<%-- HEADER --%>
<%--    </div>--%>

<div class="container">
    <div class="row mt-5">

        <div class="col-md-6 order-md-1 mb-5">
            <%--    user info--%>
            <table class="table">

                <h3><fmt:message key="user_page.user_info"/></h3>
                <thead>
                <tr>
                    <th><fmt:message key="login.login_form.login"/></th>
                    <th><fmt:message key="registration.registration_form.first_name"/></th>
                    <th><fmt:message key="registration.registration_form.last_name"/></th>
                </tr>
                </thead>
                <tr>
                    <td>${user.login}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                </tr>
            </table>
        </div>

        <div class="col-md-6 order-md-2 mb-5">
            <div class="table-responsive-sm">
                <h3><fmt:message key="user_page.user_results"/></h3>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th><fmt:message key="user_page.user_results.test_name"/></th>
                        <th><fmt:message key="user_page.user_results.evaluation_date"/></th>
                        <th><fmt:message key="user_page.user_results.evaluation"/></th>
                    </tr>
                    </thead>
                    <c:forEach items="${userResultList}" var="userResult" varStatus="loop">
                        <tr>
                            <td>${userResult.testName}</td>

                            <td>${userResult.evaluationDate}</td>
                            <td>${userResult.evaluation}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>


<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>

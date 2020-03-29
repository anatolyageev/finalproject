<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 3/27/2020
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<html>

<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%-- HEADER --%>

<div>

    <fmt:message key="user_list.change_role" var="changeRole"/>
    <fmt:message key="user_list.access_level.user" var="user_al"/>
    <fmt:message key="user_list.access_level.admin" var="admin_al"/>
    <%-- CONTENT --%>


    <table id="list_users">
        <thead>
        <tr>
            <th>ID</th>
            <th>login</th>

            <th>firstName</th>
            <th>lastName</th>
            <th>role</th>
            <th>${changeRole}</th>
        </tr>
        </thead>


        <c:forEach items="${requestScope.userList}" var="user" varStatus="loop">

            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>

                <td>${user.firstName} </td>
                <td>${user.lastName}</td>
                <td>

                    <c:choose>
                        <c:when test="${user.roleId == 0}">
                            ${admin_al}
                        </c:when>
                        <c:otherwise>
                            ${user_al}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>

                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="changeRoleCommand"/>
                            <input type="hidden" name="user_id" value="${user.id}">
                            <input type="submit" value="${changeRole}">
                            <input type="hidden" name="user_current_role" value="${user.roleId}">
                        </form>
                </td>
            </tr>

        </c:forEach>
    </table>


    <%-- CONTENT --%>


</div>


<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>

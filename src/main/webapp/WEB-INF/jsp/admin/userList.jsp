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
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>
<div class="container">
<div>

    <fmt:message key="user_list.change_role" var="changeRole"/>
    <fmt:message key="user_list.change_status" var="changeStatus"/>
    <fmt:message key="user_list.access_level.user" var="user_al"/>
    <fmt:message key="user_list.access_level.admin" var="admin_al"/>
    <fmt:message key="user_list.access_level.active" var="act_us"/>
    <fmt:message key="user_list.access_level.not_active" var="not_act_us"/>
    <%-- CONTENT --%>


    <table id="list_users" class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>login</th>

            <th>firstName</th>
            <th>lastName</th>
            <th>role</th>
            <th>status</th>
            <th>${changeRole}</th>
            <th>${changeStatus}</th>
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
                    <c:choose>
                        <c:when test="${user.userStatus == true}">
                            ${act_us}
                        </c:when>
                        <c:otherwise>
                            ${not_act_us}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="changeRoleCommand"/>
                            <input type="hidden" name="user_id" value="${user.id}">
                            <input type="hidden" name="user_current_role" value="${user.roleId}">
                            <button type="submit" class = "button btn-info">${changeRole}</button>
                        </form>
                </td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="changeUserStatusCommand"/>
                        <input type="hidden" name="user_id" value="${user.id}">
                        <input type="hidden" name="user_current_status" value="${user.userStatus}">
                        <button type="submit" class = "button btn-warning">${changeStatus}</button>
                    </form>
                </td>
            </tr>

        </c:forEach>
    </table>


    <%-- CONTENT --%>


</div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>

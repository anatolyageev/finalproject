<%--===========================================================================
Header (top area).
===========================================================================--%>

<%@ page import="ua.nure.ageev.finaltask4.domain.Role" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:if test="${not empty user}">

    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
            <c:choose>

                <%--===========================================================================
                This way we define the ADMIN MENU.
                ===========================================================================--%>
                <c:when test="${userRole.name == 'admin' }">
                    <a class="navbar-brand" href="controller?command=listOrders">All orders</a> &nbsp;
                </c:when>


                <%--===========================================================================
                This way we define the USER MENU.
                ===========================================================================--%>
                <c:when test="${userRole.name == 'client'}">
                    <a class="navbar-brand" href="controller?command=listMenu">Menu</a> &nbsp;
                </c:when>
            </c:choose>


            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample09"
                    aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarsExample09">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="controller?command=adminPageCommand"><fmt:message
                                key="admin.heading_admin"/></a> &nbsp;&nbsp;
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">

                        <a class="nav-link" href="controller?command=viewSettings"><fmt:message
                                key="header.menu_settings"/></a> &nbsp;

                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown09"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span
                                class="flag-icon flag-icon-us"> </span> ${sessionScope.currentLocale} </a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item" href="changeLocale.jsp?locale=en"><fmt:message
                                    key="local.local_button.name.en"/></a>
                            <a class="dropdown-item" href="changeLocale.jsp?locale=ru"><fmt:message
                                    key="local.local_button.name.ru"/></a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>





    <tr>
    <td id="header">
    <%--===========================================================================
    This way we define the menu located at the right of header.
    ===========================================================================--%>
    <div id="rightHeader">

            <%--===========================================================================
            Type user name if the user object is presented in the current session.
            ===========================================================================--%>
        <c:out value="${user.firstName} ${user.lastName}"/>

            <%--===========================================================================
            Type user role name if the user object is presented in the current session.
            ===========================================================================--%>
        <c:if test="${not empty userRole}">
            <c:out value="(${userRole.name})"/>
        </c:if>

            <%--===========================================================================
            Type link to logout
            ===========================================================================--%>
        <a href="controller?command=logout">
            Logout
        </a>

    </div>
</c:if>

<c:if test="${empty user and title ne 'Login'}">
    <div id="rightHeader">
        <a href="login.jsp">Login</a>
    </div>
</c:if>


</td>
</tr>


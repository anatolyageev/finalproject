<%--===========================================================================
Header (top area).
===========================================================================--%>

<%@ page import="ua.nure.ageev.finaltask4.domain.Role" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:if test="${not empty user}">
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">

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
                <a class="navbar-brand" href="controller?command=listMenu">Quizzes</a> &nbsp;
            </c:when>
        </c:choose>



        <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-collapse collapse" id="navbarCollapse" style="">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Disabled</a>
                </li>
            </ul>
            <form class="form-inline mt-2 mt-md-0">
                <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</header>



<%--    <div class="container-fluid">--%>
<%--    <header>--%>
<%--        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">--%>

<%--&lt;%&ndash;        <nav class="navbar navbar-expand-md navbar-light fixed-top bg-light rounded">&ndash;%&gt;--%>
<%--            <c:choose>--%>

<%--                &lt;%&ndash;===========================================================================--%>
<%--                This way we define the ADMIN MENU.--%>
<%--                ===========================================================================&ndash;%&gt;--%>
<%--                <c:when test="${userRole.name == 'admin' }">--%>
<%--                    <a class="navbar-brand" href="controller?command=listOrders">All orders</a> &nbsp;--%>
<%--                </c:when>--%>


<%--                &lt;%&ndash;===========================================================================--%>
<%--                This way we define the USER MENU.--%>
<%--                ===========================================================================&ndash;%&gt;--%>
<%--                <c:when test="${userRole.name == 'client'}">--%>
<%--                    <a class="navbar-brand" href="controller?command=listMenu">Quizzes</a> &nbsp;--%>
<%--                </c:when>--%>
<%--            </c:choose>--%>


<%--            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample09"--%>
<%--                    aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                <span class="navbar-toggler-icon"></span>--%>
<%--            </button>--%>
<%--            <div class="collapse navbar-collapse" id="navbarsExample09">--%>
<%--                <ul class="navbar-nav mr-auto">--%>
<%--                    <li class="nav-item active">--%>
<%--                        <a class="nav-link" href="controller?command=adminPageCommand"><fmt:message--%>
<%--                                key="admin.heading_admin"/></a> &nbsp;&nbsp;--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="#">Link</a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>

<%--                        <a class="nav-link" href="controller?command=viewSettings"><fmt:message--%>
<%--                                key="header.menu_settings"/></a> &nbsp;--%>

<%--                    </li>--%>
<%--                    <li class="nav-item dropdown">--%>
<%--                        <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown09"--%>
<%--                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span--%>
<%--                                class="flag-icon flag-icon-us"> </span> ${sessionScope.currentLocale} </a>--%>
<%--                        <div class="dropdown-menu" aria-labelledby="dropdown09">--%>
<%--                            <a class="dropdown-item" href="changeLocale.jsp?locale=en"><fmt:message--%>
<%--                                    key="local.local_button.name.en"/></a>--%>
<%--                            <a class="dropdown-item" href="changeLocale.jsp?locale=ru"><fmt:message--%>
<%--                                    key="local.local_button.name.ru"/></a>--%>
<%--                        </div>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--        </nav>--%>
<%--    </header>--%>
<%--    </div>--%>





<%--    <tr>--%>
<%--    <td id="header">--%>
<%--    &lt;%&ndash;===========================================================================--%>
<%--    This way we define the menu located at the right of header.--%>
<%--    ===========================================================================&ndash;%&gt;--%>
<%--    <div id="rightHeader">--%>

<%--            &lt;%&ndash;===========================================================================--%>
<%--            Type user name if the user object is presented in the current session.--%>
<%--            ===========================================================================&ndash;%&gt;--%>
<%--        <c:out value="${user.firstName} ${user.lastName}"/>--%>

<%--            &lt;%&ndash;===========================================================================--%>
<%--            Type user role name if the user object is presented in the current session.--%>
<%--            ===========================================================================&ndash;%&gt;--%>
<%--        <c:if test="${not empty userRole}">--%>
<%--            <c:out value="(${userRole.name})"/>--%>
<%--        </c:if>--%>

<%--            &lt;%&ndash;===========================================================================--%>
<%--            Type link to logout--%>
<%--            ===========================================================================&ndash;%&gt;--%>
<%--        <a href="controller?command=logout">--%>
<%--            Logout--%>
<%--        </a>--%>

<%--    </div>--%>
</c:if>

<c:if test="${empty user and title ne 'Login'}">

    <header>
        <!-- Fixed navbar -->
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="login.jsp">Login</a>
        </nav>
    </header>




<%--    <div id="rightHeader">--%>
<%--        <a href="login.jsp">Login</a>--%>
<%--    </div>--%>
</c:if>





<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="curr_lang" type="java.util.Locale" required="true" %>
<%@ attribute name="curr_uri" type="java.lang.String" required="true" %>
<fmt:setBundle basename="local"/>

<c:choose>
    <c:when test="${sessionScope.currentLocale == 'ru'}">
        <fmt:setLocale value="ru"/>
        <fmt:setBundle basename="local"/>
        <form class="form-inline mt-2 mt-md-0" action="controller" method="post">
            <fmt:message key="local.local_button.english" var="en_button"/>
            <input type="hidden" name="command" value="changeLanguageCommand"/>
            <input type="hidden" name="lang" value="en"/>
            <input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="${en_button}"/>
            <br>
        </form>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
        <fmt:setBundle basename="local"/>
        <form class="form-inline mt-2 mt-md-0" action="controller" method="post">
            <fmt:message key="local.local_button.russian" var="ru_button"/>
            <input type="hidden" name="command" value="changeLanguageCommand"/>
            <input type="hidden" name="lang" value="ru"/>
            <input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="${ru_button}"/><br>
        </form>
    </c:otherwise>
</c:choose>



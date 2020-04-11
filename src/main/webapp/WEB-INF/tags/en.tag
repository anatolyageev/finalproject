<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="local"/>

<fmt:setLocale value="en"/>
<fmt:setBundle basename="local"/>


<fmt:message key="local.local_button.english" var="en_button"/>


<c:set var="currentLocale" value="en" scope="session"/>
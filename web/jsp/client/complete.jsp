<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="auth" uri="http://matskevich.net/taglib/auth" %>
<%@taglib prefix="n" uri="http://matskevich.net/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="order.success.title"/></title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>

<%@include file="../../WEB-INF/jspf/header.jspf"%>


<div class="content">
    <div class="order-complete">
        <h1><fmt:message key="order.success.title"/></h1>
        <p><fmt:message key="order.success.thanks">
            <fmt:param value="${tour.tourname}"/>
            </fmt:message>
        </p>
        <a href="app?c=account&lang=${locale}"><fmt:message key="order.success.goto"/></a>
    </div>

</div>


<%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>
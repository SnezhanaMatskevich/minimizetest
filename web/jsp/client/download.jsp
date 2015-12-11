<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="auth" uri="http://matskevich.net/taglib/auth" %>
<%@taglib prefix="n" uri="http://matskevich.net/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>
<html>
    <head>
        <title><fmt:message key="tour.title"/></title>
        <link rel="stylesheet" href="css/style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    </head>
    <body>

    <%@include file="../../WEB-INF/jspf/header.jspf"%>

        <div class="content" id="tours">
            <div class="tour-view">

                <h1>Закачка завершена </h1>
                <p>Отёт находится в папку Reports на диске C</p>

            </div>

        </div>

  <%@include file="../../WEB-INF/jspf/footer.jspf"%>

    </body>
</html>
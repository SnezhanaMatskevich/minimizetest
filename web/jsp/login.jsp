<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="auth" uri="http://matskevich.net/taglib/auth" %>
<%@taglib prefix="n" uri="http://matskevich.net/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="auth.page.title"/></title>
    <link rel="stylesheet" href="css/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<%@include file="../WEB-INF/jspf/header.jspf"%>

<div class="content">

    <h1><fmt:message key="auth.page.title"/> </h1>
    <p><fmt:message key="auth.page.message"/> </p>
    <form method="post" id="loginForm">
        <input type="hidden" name="command" value="login"/>
        <label for="login"><fmt:message key="auth.page.login_form.login"/> :</label>
        <input id="login" type="text" name="login"/>
        <label for="password"><fmt:message key="auth.page.login_form.password"/>:</label>
        <input id="password" type="password" name="password"/>
        <input type="submit" value="<fmt:message key="auth.page.login_form.submit"/>"/>
    </form>
</div>
        <br>
<%@include file="../WEB-INF/jspf/footer.jspf"%>
</body>
</html>
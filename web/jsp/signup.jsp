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
        <title><fmt:message key="signup.page.title"/></title>
        <link rel="stylesheet" href="css/style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    </head>
    <body>

        <%@include file="../WEB-INF/jspf/header.jspf"%>

        <div class="content">
            <form class="sign-up" method="post" id="loginForm">
                <h3 class="sign-up-title"><fmt:message key="signup.page.title"/> </h3>
                <input type="hidden" name="command" value="user"/>               
                <input class="sign-up-input" id="login" type="text" name="login" pattern ='^[a-zA-Z]+$' placeholder =<fmt:message key="auth.page.login_form.login"/> required/>
                <input class="sign-up-input" id="password" type="password" pattern = '^[a-zA-Z0-9]+$'placeholder= <fmt:message key="auth.page.login_form.password"/> name="password" required/>
                <input class="sign-up-input" id="firstName" type="text"pattern ='^[а-яА-ЯёЁa-zA-Z]+$' placeholder=<fmt:message key="signup.page.login_form.firstname"/> name="firstName" required/>
                <input class="sign-up-input" id="lastName" type="text" pattern ='^[а-яА-ЯёЁa-zA-Z]+$' placeholder=<fmt:message key="signup.page.login_form.lastname"/> name="lastName" required/>
                <input class="sign-up-input" id="dateOfBirth" type="date" placeholder=<fmt:message key="signup.page.login_form.dateofbirth"/> name="dateOfBirth" min="1890-01-01"/>
                <input class="sign-up-input" id="email" type="email" placeholder=<fmt:message key="signup.page.login_form.email"/> name="email"required/>
                <input class="sign-up-input" id="fullAdress" type="text" placeholder=<fmt:message key="signup.page.login_form.fulladress"/> name="fullAdress"required />
                <input class="sign-up-button" type="submit" value="<fmt:message key="signup.page.login_form.submit"/>"/>
            </form>

        </div>

        <%@include file="../WEB-INF/jspf/footer.jspf"%>
    </body>
</html>